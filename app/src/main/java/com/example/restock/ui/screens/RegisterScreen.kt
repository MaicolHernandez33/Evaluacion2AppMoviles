package com.example.restock.ui.screens

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
fun RegisterScreen(
    onRegistered: () -> Unit = {},
    onGoToLogin: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmClave by remember { mutableStateOf("") }
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
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

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

        TextField(
            value = confirmClave,
            onValueChange = { confirmClave = it },
            label = { Text("Confirmar Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                mensaje = when {
                    nombre.isEmpty()       -> "El campo Nombre Completo es obligatorio."
                    correo.isEmpty()       -> "El campo Correo Electrónico es obligatorio."
                    clave.isEmpty()        -> "El campo Contraseña es obligatorio."
                    confirmClave.isEmpty() -> "El campo Confirmar Contraseña es obligatorio."
                    clave != confirmClave  -> "Las contraseñas no coinciden."
                    else -> {
                        onRegistered()
                        "Registro exitoso: \nNombre: $nombre\nCorreo: $correo\n"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8572A),
                contentColor   = Color(0xFFFFFFFF)
            )
        ) { Text("Registrar") }

        Spacer(Modifier.height(5.dp))

        Button(
            onClick = { onGoToLogin() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xB0DE7651),
                contentColor   = Color(0xFFFFFFFF)
            )
        ) { Text("Iniciar Sesión") }

        if (mensaje.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = mensaje,
                fontSize = 18.sp,
                color = if (mensaje.contains("Registro exitoso"))
                    Color(0xFF2E7D32) else Color(0xFFc62828)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRegisterScreen() {
    RestockTheme { RegisterScreen() }
}
