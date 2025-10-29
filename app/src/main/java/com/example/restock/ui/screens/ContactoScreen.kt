package com.example.restock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ContactoScreen(
    onBack: () -> Unit = {},
    onEnviar: (nombre: String, correo: String, mensaje: String) -> Unit = { _,_,_ -> }
) {
    val peach = Color(0xFFEAAC8B)
    val orange = Color(0xFFD8572A)

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }

    fun correoValido(s: String) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(s.trim()).matches()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(peach)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = orange, contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) { Text("Volver") }

            Spacer(Modifier.width(12.dp))
            Text("Contacto", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(Modifier.height(12.dp))

        // Card con el formulario
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = mensaje,
                    onValueChange = { mensaje = it },
                    label = { Text("Mensaje") },
                    minLines = 4,
                    maxLines = 6,
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier.fillMaxWidth()
                )

                if (feedback.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = feedback,
                        color = if (feedback.contains("enviado", true))
                            Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                }

                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        val n = nombre.trim()
                        val c = correo.trim()
                        val m = mensaje.trim()
                        feedback = when {
                            n.isBlank() -> "Completa tu nombre."
                            c.isBlank() -> "Completa tu correo."
                            !correoValido(c) -> "Correo inválido."
                            m.isBlank() -> "Escribe tu mensaje."
                            else -> {
                                onEnviar(n, c, m)     // aquí podrías enviar a backend/email
                                "Mensaje enviado. ¡Gracias por escribirnos!"
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orange, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Enviar") }
            }
        }
    }
}
