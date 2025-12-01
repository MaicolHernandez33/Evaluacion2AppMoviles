package com.example.restock.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.ui.components.CommonBackground
import com.example.restock.viewmodel.VistaModeloUsuarios
import com.example.restock.ui.theme.naranjo

@Composable
fun CRUDUsuariosScreen(vm: VistaModeloUsuarios, onBack: () -> Unit) {

    // Cargar usuarios al entrar
    LaunchedEffect(Unit) { vm.cargar() }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var editando by remember { mutableStateOf(false) }

    var errorMsg by remember { mutableStateOf("") }

    val lista = vm.usuarios

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

            // TÍTULO
            Surface(
                color = Color.Black.copy(alpha = 0.45f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "Administrar Usuarios",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            // ---------- FORMULARIO ----------
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.92f)
                )
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { if (!editando) correo = it },
                        label = { Text("Correo") },
                        enabled = !editando,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(20.dp))

                    // ---------- VALIDACIONES ----------
                    if (errorMsg.isNotEmpty()) {
                        Text(
                            text = errorMsg,
                            color = Color(0xFFC62828),
                            fontSize = 15.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }

                    // ---------- BOTÓN AGREGAR / EDITAR ----------
                    Button(
                        onClick = {

                            val n = nombre.trim()
                            val c = correo.trim()
                            val p = pass.trim()

                            // Validaciones
                            errorMsg = when {
                                n.isBlank() -> "El nombre no puede estar vacío."
                                c.isBlank() -> "El correo no puede estar vacío."
                                !android.util.Patterns.EMAIL_ADDRESS.matcher(c).matches() ->
                                    "El correo ingresado no es válido."
                                p.isBlank() -> "La contraseña no puede estar vacía."
                                p.length < 6 -> "La contraseña debe tener al menos 6 caracteres."
                                else -> ""
                            }

                            if (errorMsg.isNotEmpty()) return@Button

                            // Guardar o agregar
                            if (editando) {
                                vm.eliminar(c)
                                vm.agregar(n, c, p)
                                editando = false
                            } else {
                                vm.agregar(n, c, p)
                            }

                            nombre = ""
                            correo = ""
                            pass = ""
                        },
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(if (editando) "Guardar Cambios" else "Agregar")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ---------- LISTA DE USUARIOS ----------
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(lista) { user ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.85f)
                        ),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            // Izquierda: icono + datos
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = naranjo,
                                    modifier = Modifier.size(32.dp)
                                )

                                Spacer(Modifier.width(12.dp))

                                Column {
                                    Text(user.nombre, fontWeight = FontWeight.Bold)
                                    Text(user.correo, color = Color.Gray)
                                }
                            }

                            // Derecha: botones acciones
                            Row {

                                IconButton(onClick = {
                                    nombre = user.nombre
                                    correo = user.correo
                                    pass = user.password
                                    editando = true
                                }) {
                                    Icon(Icons.Default.Edit, "Editar", tint = naranjo)
                                }

                                IconButton(onClick = {
                                    vm.eliminar(user.correo)
                                }) {
                                    Icon(Icons.Default.Delete, "Eliminar", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
