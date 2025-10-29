package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.restock.ui.screens.*
import com.example.restock.ui.theme.RestockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestockTheme {
                var screen by rememberSaveable { mutableStateOf("login") }

                when (screen) {
                    "login" -> LoginScreen(
                        onLoginOK = { screen = "home" },
                        onGoToRegister = { screen = "register" }
                    )

                    "register" -> RegisterScreen(
                        onRegistered = { screen = "home" },
                        onGoToLogin = { screen = "login" }
                    )

                    "home" -> HomeScreen(
                        onGoToCatalogo = { screen = "catalogo" },
                        onGoToNosotros = { screen = "nosotros" },
                        onGoToContacto = { screen = "contacto" },
                        onLogout = { screen = "login" }
                    )


                    "nosotros" -> NosotrosScreen(
                        onBack = { screen = "home" }
                    )

                    "contacto" -> ContactoScreen(
                        onBack = { screen = "home" },
                        onEnviar = { nombre, correo, msg ->
                            // TODO: enviar/guardar mensaje
                        }
                    )
                }
            }
        }
    }
}
