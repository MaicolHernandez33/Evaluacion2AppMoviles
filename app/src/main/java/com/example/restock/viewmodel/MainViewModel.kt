package com.example.restock.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.restock.data.BaseDatosHelper
import com.example.restock.data.LocalStorage
import com.example.restock.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Rutas/pantallas simples
enum class Screen { LOGIN, REGISTER, HOME, CATALOGO, NOSOTROS, CONTACTO, CARRITO, PERFIL, QRSCANNER, CRUDUSUARIOS  }

data class UiState(
    val screen: Screen = Screen.LOGIN,
    val isLoading: Boolean = false,
    val userName: String? = null,
    val message: String? = null
)

class MainViewModel(app: Application) : AndroidViewModel(app) {

    //para microservicio productos
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://productservice-ly24.onrender.com/") // el / al final es necesario para RetroFit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiService::class.java)

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                val resultado = api.getProductos()
                _productos.value = resultado
            } catch (e: Exception) {
                _productos.value = listOf(Producto(0L, "Error: ${e.message}", 0.0,""))
            }
        }
    }


    val carrito = mutableStateListOf<Producto>()
    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui

    private val appCtx = getApplication<Application>().applicationContext

    init {
        // Al iniciar, si hay sesión marcada, ir directo al HOME
        viewModelScope.launch {
            val logged = LocalStorage.isLogged(appCtx)
            val name   = LocalStorage.getUserName(appCtx)
            _ui.value = _ui.value.copy(
                screen = if (logged) Screen.HOME else Screen.LOGIN,
                userName = name
            )
        }
    }

    fun goTo(s: Screen) {
        _ui.value = _ui.value.copy(screen = s, message = null)
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, message = null)

            val db = BaseDatosHelper(appCtx)
            val usuarioDb = db.listar().find { it.correo == email.trim() && it.password == pass.trim() }

            if (usuarioDb != null) {
                _ui.value = _ui.value.copy(
                    isLoading = false,
                    screen = Screen.HOME,
                    userName = usuarioDb.nombre,
                    message = "Inicio de sesión correcto (SQLite)."
                )
                return@launch
            }

            // Revisar LocalStorage como respaldo
            val ok = LocalStorage.checkLogin(appCtx, email.trim(), pass.trim())
            if (ok) {
                val name = LocalStorage.getUserName(appCtx)
                _ui.value = _ui.value.copy(
                    isLoading = false,
                    screen = Screen.HOME,
                    userName = name,
                    message = "Inicio de sesión correcto (LocalStorage)."
                )
            } else {
                _ui.value = _ui.value.copy(
                    isLoading = false,
                    message = "Correo o contraseña incorrectos."
                )
            }
        }
    }

    fun register(name: String, email: String, pass: String) {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, message = null)
            LocalStorage.saveUser(appCtx, name.trim(), email.trim(), pass.trim())
            // opcional: marcar sesión o volver al login
            _ui.value = _ui.value.copy(isLoading = false, screen = Screen.HOME, userName = name, message = "Registro exitoso.")
        }
    }

    fun logout() {
        viewModelScope.launch {
            LocalStorage.logout(appCtx)
            _ui.value = _ui.value.copy(screen = Screen.LOGIN, userName = null, message = null)
        }
    }
}
