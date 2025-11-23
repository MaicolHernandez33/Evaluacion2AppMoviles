package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restock.ui.screens.*
import com.example.restock.ui.theme.RestockTheme
import com.example.restock.viewmodel.MainViewModel
import com.example.restock.viewmodel.Screen
import com.example.restock.viewmodel.VistaModeloUsuarios
import com.example.restock.viewmodel.VistaModeloUsuariosFactory

const val URL_KEY: String = "UrlKey"

class MainActivity : ComponentActivity() {

    private var urlText by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            RestockTheme {

                val vm: MainViewModel = viewModel()
                val ui by vm.ui.collectAsState()

                Crossfade(ui.screen, label = "screen") { sc ->
                    when (sc) {

                        // LOGIN
                        Screen.LOGIN -> LoginScreen(
                            onLoginOK = {},
                            onGoToRegister = { vm.goTo(Screen.REGISTER) },
                            onSubmitLogin = { email, pass -> vm.login(email, pass) },
                            snack = ui.message
                        )

                        // REGISTER
                        Screen.REGISTER -> RegisterScreen(
                            onRegistered = {},
                            onGoToLogin = { vm.goTo(Screen.LOGIN) },
                            onSubmitRegister = { name, email, pass ->
                                vm.register(name, email, pass)
                            },
                            snack = ui.message
                        )

                        // HOME
                        Screen.HOME -> HomeScreen(
                            onGoToCatalogo = { vm.goTo(Screen.CATALOGO) },
                            onGoToNosotros = { vm.goTo(Screen.NOSOTROS) },
                            onGoToContacto = { vm.goTo(Screen.CONTACTO) },
                            onGoToProfile = { vm.goTo(Screen.PERFIL) },
                            onGoToQrScreen = { vm.goTo(Screen.QRSCANNER) },
                            onGoToCRUDUsuarios = { vm.goTo(Screen.CRUDUSUARIOS) },
                            onLogout = { vm.logout() }
                        )

                        // CATÁLOGO
                        Screen.CATALOGO -> CatalogoScreen(
                            onBack = { vm.goTo(Screen.HOME) },
                            onGoToCarrito = { vm.goTo(Screen.CARRITO) },
                            carrito = vm.carrito
                        )

                        // NOSOTROS
                        Screen.NOSOTROS -> NosotrosScreen(
                            onBack = { vm.goTo(Screen.HOME) }
                        )

                        // CONTACTO
                        Screen.CONTACTO -> ContactoScreen(
                            onBack = { vm.goTo(Screen.HOME) },
                            onEnviar = { _, _, _ -> }
                        )

                        // CARRITO
                        Screen.CARRITO -> CarritoScreen(
                            carrito = vm.carrito,
                            onBack = { vm.goTo(Screen.CATALOGO) }
                        )

                        // PERFIL
                        Screen.PERFIL -> ProfileScreen(
                            onBack = { vm.goTo(Screen.HOME) }
                        )

                        // ⭐ CRUD USUARIOS (correcto!)
                        Screen.CRUDUSUARIOS -> {
                            val vmUsuarios: VistaModeloUsuarios = viewModel(
                                factory = VistaModeloUsuariosFactory(this@MainActivity)
                            )

                            CRUDUsuariosScreen(
                                vm = vmUsuarios,
                                onBack = { vm.goTo(Screen.HOME) }
                            )
                        }

                        // QR SCANNER
                        Screen.QRSCANNER -> {
                            var localUrlText by remember { mutableStateOf(urlText) }

                            QrScannerScreen(
                                onBack = { vm.goTo(Screen.HOME) },
                                urlText = localUrlText,
                                onUrlTextUpdate = {
                                    localUrlText = it
                                    urlText = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(URL_KEY, urlText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val saved = savedInstanceState.getString(URL_KEY)
        if (saved != null) urlText = saved
    }
}
