package com.example.restock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.ui.screens.auth.LoginScreen
import com.example.restock.ui.screens.auth.RegisterScreen
import com.example.restock.ui.screens.home.HomeScreen
import com.example.restock.ui.screens.*
import com.example.restock.ui.theme.RestockTheme
import com.example.restock.ui.theme.titulos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreat e(savedInstanceState)
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


@Composable
fun loginScreen() {
    // Estados o variables de mi app
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmClave by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupe toda la pantalla del celular
            .background(Color(0xFFEAAC8B)) // Para agregar un color 0xFF______
            .padding(24.dp), // dp = Se adapta a los pixeles de la pantalla
        horizontalAlignment = Alignment.CenterHorizontally, // Centrado horizontal
        verticalArrangement = Arrangement.Center // Centrado vertical
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Cargamos el logo
            contentDescription = "Logotipo Oficial Del restaurante",
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 32.dp)
        )
        Text(text="¡Bienvenid@!", fontSize = 30.sp, color= titulos)
        Spacer(modifier = Modifier.height(10.dp))

        // Campo Nombre Completo
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo Correo Electrónico
        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo Contraseña
        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation() // Ocultamos caracteres
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo Confirmar Contraseña
        TextField(
            value = confirmClave,
            onValueChange = { confirmClave = it },
            label = { Text("Confirmar Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation() // Ocultamos caracteres
        )

        Spacer(modifier = Modifier.height(20.dp))


        // Botón de Registrar
        Button(
            onClick = {
                // Validación
                when {
                    nombre.isEmpty() -> mensaje = "El campo Nombre Completo es obligatorio."
                    correo.isEmpty() -> mensaje = "El campo Correo Electrónico es obligatorio."
                    clave.isEmpty() -> mensaje = "El campo Contraseña es obligatorio."
                    confirmClave.isEmpty() -> mensaje = "El campo Confirmar Contraseña es obligatorio."

                    clave != confirmClave -> mensaje = "Las contraseñas no coinciden."
                    else -> mensaje = "Registro exitoso: \nNombre: $nombre\nCorreo: $correo\n"
                }
            },


            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8572A), // Color fondo
                contentColor = Color(0xFFFFFFFF) // Color de texto
            )
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(5.dp))


        // Botón de login
        Button(
            onClick = {
                // Validación
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xB0DE7651), // Color fondo
                contentColor = Color(0xFFFFFFFF) // Color de texto
            )
        ) {
            Text("Iniciar Sesión")
        }



        // Mostrar mensaje de validación
        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                fontSize = 18.sp,
                color = if (mensaje.contains("Registro exitoso")) Color(0xFF2E7D32) else Color(0xFFc62828)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RestockTheme {
        loginScreen()
    }
}

