package com.example.restock.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.naranjo

@Composable
fun RegisterScreen(
    onRegistered: () -> Unit = {},
    onGoToLogin: () -> Unit = {},
    onSubmitRegister: (String, String, String) -> Unit = { _, _, _ -> },
    snack: String? = null
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmClave by remember { mutableStateOf("") }
    var mensajeLocal by remember { mutableStateOf("") }

    fun validar(): String? {
        if (nombre.isBlank()) return "El campo Nombre Completo es obligatorio."
        if (correo.isBlank()) return "El campo Correo Electrónico es obligatorio."
        if (!Patterns.EMAIL_ADDRESS.matcher(correo.trim()).matches()) return "Correo electrónico inválido."
        if (clave.isBlank()) return "El campo Contraseña es obligatorio."
        if (confirmClave.isBlank()) return "El campo Confirmar Contraseña es obligatorio."
        if (clave.length < 6) return "La contraseña debe tener al menos 6 caracteres."
        if (clave != confirmClave) return "Las contraseñas no coinciden."
        return null
    }

    CommonBackground {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(bottom = 24.dp)
            )

            // TARJETA
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // NOMBRE
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre Completo") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "nombre",
                                tint = naranjo
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    // CORREO
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo Electrónico") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "email",
                                tint = naranjo
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    // CONTRASEÑA
                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "password",
                                tint = naranjo
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    // CONFIRMAR CONTRASEÑA
                    OutlinedTextField(
                        value = confirmClave,
                        onValueChange = { confirmClave = it },
                        label = { Text("Confirmar Contraseña") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "password-confirm",
                                tint = naranjo
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(20.dp))

                    // BOTÓN REGISTRAR
                    Button(
                        onClick = {
                            validar()?.let { mensajeLocal = it } ?: run {
                                onSubmitRegister(nombre.trim(), correo.trim(), clave.trim())
                                mensajeLocal = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("Registrar", fontSize = 16.sp)
                    }

                    Spacer(Modifier.height(12.dp))

                    // BOTÓN INICIAR SESIÓN
                    OutlinedButton(
                        onClick = onGoToLogin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = naranjo
                        )
                    ) {
                        Text("Iniciar Sesión", fontSize = 16.sp)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            if (mensajeLocal.isNotEmpty()) {
                Text(mensajeLocal, color = Color(0xFFD32F2F))
            }

            if (!snack.isNullOrBlank()) {
                Text(
                    snack,
                    color = if (snack.contains("exitoso", true)) Color(0xFF2E7D32) else Color(0xFFD32F2F)
                )
            }
        }
    }
}
