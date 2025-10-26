package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.restock.ui.screens.auth.LoginScreen
import com.example.restock.ui.screens.auth.RegisterScreen
import com.example.restock.ui.screens.home.HomeScreen
import com.example.restock.ui.theme.RestockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestockTheme {
                // login -> register -> home (estado simple y guardable)
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
                        onGoToCatalogo = { /* TODO */ },
                        onGoToNosotros = { /* TODO */ },
                        onGoToContacto = { /* TODO */ },
                        onLogout = { screen = "login" }
                    )
                }
            }
        }
    }
}
