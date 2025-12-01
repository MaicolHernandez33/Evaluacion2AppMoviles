package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.restock.model.Producto
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.*

@Composable
fun CarritoScreen(
    carrito: MutableList<Producto>,
    onBack: () -> Unit
) {

    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header fijo en la parte superior
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Volver")
                }

                Text(
                    "Carrito",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Espacio para balancear (reemplaza el botón que estaba aquí)
                Spacer(Modifier.width(80.dp))
            }

            Spacer(Modifier.height(16.dp))

            if (carrito.isEmpty()) {
                // Carrito vacío - centrado
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Tu carrito está vacío",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            } else {
                // Lista de productos con botón fijo abajo
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(carrito.size) { index ->
                            val (id,nombre, precio, urlImagen) = carrito[index]
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(0.95f),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = naranjo),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            id.toString(),
                                            color = naranjo,

                                            )
                                        Image(
                                            painter = rememberAsyncImagePainter(urlImagen),
                                            contentDescription = urlImagen,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .padding(4.dp)
                                        )
                                        Spacer(Modifier.width(12.dp))
                                        Text(
                                            nombre,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(Modifier.width(12.dp))
                                        Text(
                                            precio.toString(),
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.weight(1f) // ← Permite que el texto ocupe espacio
                                        )

                                    }

                                    Spacer(Modifier.width(8.dp))

                                    Button(
                                        onClick = { carrito.removeAt(index) },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White,
                                            contentColor = naranjo
                                        ),
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier.wrapContentWidth()
                                    ) {
                                        Text("Quitar")
                                    }
                                }
                            }
                        }
                    }

                    // Botón "Vaciar carrito" fijo en la parte inferior
                    Button(
                        onClick = { carrito.clear() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = naranjo,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text("Vaciar carrito", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}