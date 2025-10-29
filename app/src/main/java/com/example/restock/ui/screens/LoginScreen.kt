package com.example.restock.ui.screens

import android.util.Patterns
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

    // reglas simples
    fun correoValido(s: String) =
        Patterns.EMAIL_ADDRESS.matcher(s.trim()).matches()

    fun validar(): String? {
        val mail = correo.trim()
        val pass = clave.trim()
        if (mail.isBlank()) return "Ingresa tu correo electrónico."
        if (!correoValido(mail)) return "Correo electrónico inválido."
        if (pass.isBlank()) return "Ingresa tu contraseña."
        if (pass.length < 6) return "La contraseña debe tener al menos 6 caracteres."
        return null
    }

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

        Button(
            onClick = {
                val err = validar()
                if (err != null) {
                    mensaje = err
                } else {
                    mensaje = "Inicio de sesión correcto."
                    onLoginOK()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xB0DE7651),
                contentColor   = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = correo.isNotBlank() && clave.isNotBlank()
        ) { Text("Iniciar Sesión") }

        Spacer(Modifier.height(5.dp))

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
