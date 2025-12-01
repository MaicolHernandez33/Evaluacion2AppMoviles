package com.example.restock.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
fun LoginScreen(
    onLoginOK: () -> Unit = {},
    onGoToRegister: () -> Unit = {},
    onSubmitLogin: (String, String) -> Unit = { _, _ -> },
    snack: String? = null
) {
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var mensajeLocal by remember { mutableStateOf("") }

    fun validar(): String? {
        val mail = correo.trim()
        val pass = clave.trim()
        if (mail.isBlank()) return "Ingresa tu correo electrónico."
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) return "Correo electrónico inválido."
        if (pass.isBlank()) return "Ingresa tu contraseña."
        if (pass.length < 6) return "La contraseña debe tener al menos 6 caracteres."
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

                    // CAMPO EMAIL
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

                    // CAMPO CONTRASEÑA
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

                    Spacer(Modifier.height(20.dp))

                    // BOTÓN INICIAR SESIÓN
                    Button(
                        onClick = {
                            validar()?.let { mensajeLocal = it } ?: run {
                                onSubmitLogin(correo.trim(), clave.trim())
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
                        Text("Iniciar Sesión", fontSize = 16.sp)
                    }

                    Spacer(Modifier.height(12.dp))

                    // BOTÓN REGISTRARSE
                    OutlinedButton(
                        onClick = onGoToRegister,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = naranjo
                        )
                    ) {
                        Text("Registrarse", fontSize = 16.sp)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // MENSAJE LOCAL
            if (mensajeLocal.isNotEmpty()) {
                Text(
                    text = mensajeLocal,
                    fontSize = 16.sp,
                    color = Color(0xFFD32F2F)
                )
            }

            // SNACK DEL VIEWMODEL
            if (!snack.isNullOrBlank()) {
                Text(
                    text = snack,
                    fontSize = 16.sp,
                    color = if (snack.contains("correcto", true))
                        Color(0xFF2E7D32)
                    else Color(0xFFD32F2F)
                )
            }
        }
    }
}
