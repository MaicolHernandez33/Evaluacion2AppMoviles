package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.*

@Composable
fun ProfileScreen(
    onBack: () -> Unit = {}
) {
    var userProfileImage by remember { mutableStateOf<Any?>(null) }
    var userName by remember { mutableStateOf("Usuario Restock") }
    var userEmail by remember { mutableStateOf("usuario@restock.cl") }
    var userPhone by remember { mutableStateOf("+56 9 1234 5678") }
    var showImagePickerDialog by remember { mutableStateOf(false) }

    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) { Text("Volver") }

                Text(
                    "Mi Perfil",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.width(80.dp))
            }

            Spacer(Modifier.height(32.dp))

            // Contenido centrado
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Foto de perfil
                        Box(
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            if (userProfileImage == null) {
                                Image(
                                    painter = painterResource(R.drawable.logo),
                                    contentDescription = "Foto de perfil",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                // Aquí iría la foto seleccionada/tomada
                                Image(
                                    painter = painterResource(R.drawable.logo),
                                    contentDescription = "Foto de perfil",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            // Botón para cambiar foto
                            FloatingActionButton(
                                onClick = { showImagePickerDialog = true },
                                modifier = Modifier.size(40.dp),
                                containerColor = naranjo,
                                contentColor = Color.White
                            ) {
                                Icon(
                                    Icons.Default.CameraAlt,
                                    contentDescription = "Cambiar foto de perfil"
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Toca el ícono para cambiar foto",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Spacer(Modifier.height(24.dp))

                        // Información del usuario
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ProfileField(
                                label = "Nombre",
                                value = userName,
                                onValueChange = { userName = it }
                            )

                            ProfileField(
                                label = "Correo electrónico",
                                value = userEmail,
                                onValueChange = { userEmail = it }
                            )

                            ProfileField(
                                label = "Teléfono",
                                value = userPhone,
                                onValueChange = { userPhone = it }
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        Button(
                            onClick = {
                                // Guardar cambios del perfil
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = naranjo,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Guardar Cambios", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }

    // Diálogo para seleccionar opción de imagen
    if (showImagePickerDialog) {
        AlertDialog(
            onDismissRequest = { showImagePickerDialog = false },
            title = { Text("Seleccionar foto de perfil") },
            text = {
                Column {
                    Text("¿Cómo quieres agregar tu foto de perfil?")
                }
            },
            confirmButton = {
                Column {
                    // Botón para tomar foto
                    Button(
                        onClick = {
                            showImagePickerDialog = false
                            // Aquí abrirás la cámara
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Tomar foto con cámara")
                    }

                    Spacer(Modifier.height(8.dp))

                    // Botón para seleccionar de galería
                    Button(
                        onClick = {
                            showImagePickerDialog = false
                            // Aquí abrirás la galería
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.PhotoLibrary, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Elegir de galería")
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showImagePickerDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}