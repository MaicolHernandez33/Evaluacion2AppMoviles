package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.restock.ui.screens.auth.LoginScreen
import com.example.restock.ui.screens.auth.RegisterScreen
import com.example.restock.ui.theme.RestockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestockTheme {
                // Con esto alternas entre Login y Registro
                var showLogin by rememberSaveable { mutableStateOf(true) }

                if (showLogin) {
                    LoginScreen(
                        onLoginOK = {
                            // aquí luego irás a Home;

                        },
                        onGoToRegister = { showLogin = false }
                    )
                } else {
                    RegisterScreen(
                        onRegistered = {
                            // después de registrarse puedes volver a login o ir a Home
                            showLogin = true
                        },
                        onGoToLogin = { showLogin = true }
                    )
                }
            }
        }
    }
}
