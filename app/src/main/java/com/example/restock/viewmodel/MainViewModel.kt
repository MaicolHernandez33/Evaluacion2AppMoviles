package com.example.restock.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.restock.data.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Rutas/pantallas simples
enum class Screen { LOGIN, REGISTER, HOME, CATALOGO, NOSOTROS, CONTACTO }

data class UiState(
    val screen: Screen = Screen.LOGIN,
    val isLoading: Boolean = false,
    val userName: String? = null,
    val message: String? = null
)

class MainViewModel(app: Application) : AndroidViewModel(app) {

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
            val ok = LocalStorage.checkLogin(appCtx, email.trim(), pass.trim())
            _ui.value = if (ok) {
                val name = LocalStorage.getUserName(appCtx)
                _ui.value.copy(isLoading = false, screen = Screen.HOME, userName = name, message = "Inicio de sesión correcto.")
            } else {
                _ui.value.copy(isLoading = false, message = "Correo o contraseña incorrectos.")
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
