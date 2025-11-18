package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restock.ui.screens.*
import com.example.restock.ui.theme.RestockTheme
import com.example.restock.viewmodel.MainViewModel
import com.example.restock.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestockTheme {
                val vm: MainViewModel = viewModel()
                val ui by vm.ui.collectAsState()

                Crossfade(ui.screen, label = "screen") { sc ->
                    when (sc) {
                        Screen.LOGIN -> LoginScreen(
                            onLoginOK = { /* el VM cambiará a HOME */ },
                            onGoToRegister = { vm.goTo(Screen.REGISTER) },
                            onSubmitLogin = { email, pass -> vm.login(email, pass) },
                            snack = ui.message
                        )

                        Screen.REGISTER -> RegisterScreen(
                            onRegistered = { /* el VM cambiará a HOME */ },
                            onGoToLogin = { vm.goTo(Screen.LOGIN) },
                            onSubmitRegister = { name, email, pass -> vm.register(name, email, pass) },
                            snack = ui.message
                        )

                        Screen.HOME -> HomeScreen(
                            onGoToCatalogo = { vm.goTo(Screen.CATALOGO) },
                            onGoToNosotros = { vm.goTo(Screen.NOSOTROS) },
                            onGoToContacto = { vm.goTo(Screen.CONTACTO) },
                            onGoToProfile = { vm.goTo(Screen.PERFIL) },
                            onLogout = { vm.logout() }
                        )


                        Screen.CATALOGO -> CatalogoScreen(onBack = { vm.goTo(Screen.HOME) }, onGoToCarrito = { vm.goTo(Screen.CARRITO) },carrito = vm.carrito)
                        Screen.NOSOTROS -> NosotrosScreen(onBack = { vm.goTo(Screen.HOME) })
                        Screen.CONTACTO -> ContactoScreen(onBack = { vm.goTo(Screen.HOME) }, onEnviar = { _,_,_ -> })
                        Screen.CARRITO -> CarritoScreen(carrito = vm.carrito, onBack = { vm.goTo(Screen.CATALOGO) })
                        Screen.PERFIL -> ProfileScreen(onBack = { vm.goTo(Screen.HOME) }
                        )
                    }
                }
            }
        }
    }
}
