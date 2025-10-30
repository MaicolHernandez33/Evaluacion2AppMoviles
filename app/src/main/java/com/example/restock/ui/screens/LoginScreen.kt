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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R

@Composable
fun LoginScreen(
    onLoginOK: () -> Unit = {},
    onGoToRegister: () -> Unit = {},
    onSubmitLogin: (String, String) -> Unit = { _, _ -> },
    snack: String? = null
) {
    var correo by remember { mutableStateOf("") }
    var clave  by remember { mutableStateOf("") }
    var mensajeLocal by remember { mutableStateOf("") }

    val Orange = Color(0xFFD8572A)

    fun validar(): String? {
        val mail = correo.trim()
        val pass = clave.trim()
        if (mail.isBlank()) return "Ingresa tu correo electrónico."
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) return "Correo electrónico inválido."
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
                validar()?.let { mensajeLocal = it } ?: run {
                    // Delegamos la lógica al ViewModel
                    onSubmitLogin(correo.trim(), clave.trim())
                    mensajeLocal = "" // limpiamos mensaje local
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor   = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Iniciar Sesión") }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onGoToRegister,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor   = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Registrarse") }

        // Mensaje local de validación
        if (mensajeLocal.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = mensajeLocal,
                fontSize = 16.sp,
                color = Color(0xFFC62828)
            )
        }
        // Mensaje que venga del ViewModel (snack)
        if (!snack.isNullOrBlank()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = snack,
                fontSize = 16.sp,
                color = if (snack.contains("correcto", true)) Color(0xFF2E7D32) else Color(0xFFC62828)
            )
        }
    }
}
