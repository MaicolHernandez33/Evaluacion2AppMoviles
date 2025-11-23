package com.example.restock.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.naranjo
import com.example.restock.viewmodel.VistaModeloUsuarios

@Composable
fun CRUDUsuariosScreen(vm: VistaModeloUsuarios, onBack: () -> Unit) {
    // Cargar los usuarios al abrir la pantalla
    LaunchedEffect(Unit) {
        vm.cargar()
    }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var editando by remember { mutableStateOf(false) } // true si estamos editando

    val lista = vm.usuarios

    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top bar con botón volver
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = naranjo, contentColor = Color.White)
                ) { Text("Volver") }
            }

            Spacer(Modifier.height(16.dp))

            // Card para el formulario
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { if (!editando) correo = it }, // bloquea la edición si editando
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !editando
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (nombre.isNotBlank() && correo.isNotBlank() && pass.isNotBlank()) {
                                if (editando) {
                                    // Guardar cambios: eliminamos y agregamos de nuevo para simplificar
                                    vm.eliminar(correo)
                                    vm.agregar(nombre, correo, pass)
                                    editando = false
                                } else {
                                    vm.agregar(nombre, correo, pass)
                                }
                                nombre = ""
                                correo = ""
                                pass = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (editando) "Guardar Cambios" else "Agregar")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Lista de usuarios
            LazyColumn {
                items(lista) { user ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(user.nombre)
                                Text(user.correo)
                            }
                            Column {
                                // Botón Editar
                                TextButton(onClick = {
                                    nombre = user.nombre
                                    correo = user.correo
                                    pass = user.password
                                    editando = true
                                }) { Text("Editar") }

                                // Botón Eliminar
                                TextButton(onClick = { vm.eliminar(user.correo) }) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
