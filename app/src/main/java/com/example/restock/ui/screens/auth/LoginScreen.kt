package com.example.restock.ui.screens.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.theme.RestockTheme

@Composable
fun LoginScreen(
    onLoginOK: () -> Unit = {},
    onGoToRegister: () -> Unit = {}
) {
    var correo by remember { mutableStateOf("") }
    var clave  by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAAC8B))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 32.dp)
        )

        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(20.dp))

        // Botón: Iniciar sesión
        Button(
            onClick = {
                mensaje = when {
                    correo.isEmpty() -> "Ingresa tu correo electrónico."
                    clave.isEmpty()  -> "Ingresa tu contraseña."
                    else -> {
                        onLoginOK()
                        "Inicio de sesión correcto."
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xB0DE7651),
                contentColor   = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Iniciar Sesión") }

        Spacer(Modifier.height(5.dp))

        // Botón: Ir a registro
        Button(
            onClick = { onGoToRegister() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8572A),
                contentColor   = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Registrarse") }

        if (mensaje.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = mensaje,
                fontSize = 18.sp,
                color = if (mensaje.contains("correcto", ignoreCase = true))
                    Color(0xFF2E7D32) else Color(0xFFc62828)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    RestockTheme { LoginScreen() }
}
