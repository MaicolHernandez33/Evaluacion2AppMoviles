package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.naranjo

@Composable
fun ContactoScreen(
    onBack: () -> Unit = {},
    onEnviar: (String, String, String) -> Unit = { _, _, _ -> }
) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }

    fun correoValido(s: String) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(s.trim()).matches()

    CommonBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // BOTÓN VOLVER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text("Volver")
                }
            }

            Spacer(Modifier.height(10.dp))

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo Restock",
                modifier = Modifier
                    .height(150.dp)
                    .padding(bottom = 8.dp)
            )

            Surface(
                color = Color.Black.copy(alpha = 0.45f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "Contacto",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                )
            }

            Spacer(Modifier.height(22.dp))

            //  TARJETA TRANSLÚCIDA
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.92f)
                ),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre Completo") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo Electrónico") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = mensaje,
                        onValueChange = { mensaje = it },
                        label = { Text("Mensaje") },
                        minLines = 4,
                        maxLines = 6,
                        visualTransformation = VisualTransformation.None,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    if (feedback.isNotBlank()) {
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = feedback,
                            color = if (feedback.contains("enviado"))
                                Color(0xFF2E7D32)
                            else Color(0xFFC62828)
                        )
                    }

                    Spacer(Modifier.height(22.dp))

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
                                    onEnviar(n, c, m)
                                    "Mensaje enviado. ¡Gracias por escribirnos!"
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Enviar", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
