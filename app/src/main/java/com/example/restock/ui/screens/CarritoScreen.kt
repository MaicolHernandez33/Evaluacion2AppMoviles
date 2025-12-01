package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    carrito: List<Producto>,
    onBack: () -> Unit
) {
    val carritoState = remember { mutableStateListOf<Producto>().apply { addAll(carrito) } }
    var total by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(carritoState.size) {
        total = carritoState.sumOf { it.precio }
    }

    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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

                Spacer(Modifier.width(80.dp))
            }

            Spacer(Modifier.height(16.dp))

            if (carritoState.isEmpty()) {
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
                        items(carritoState.size) { index ->
                            val item = carritoState[index]
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
                                            item.id.toString(),
                                            color = naranjo,
                                        )
                                        Image(
                                            painter = rememberAsyncImagePainter(item.urlImagen),
                                            contentDescription = item.urlImagen,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .padding(4.dp)
                                        )
                                        Spacer(Modifier.width(12.dp))
                                        Text(
                                            item.nombre,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(Modifier.width(12.dp))
                                        Text(
                                            item.precio.toString(),
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }

                                    Spacer(Modifier.width(8.dp))

                                    Button(
                                        onClick = { carritoState.removeAt(index) },
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

                    Spacer(Modifier.width(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Total: $${"%.2f".format(total)}",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            modifier = Modifier
                                .padding(8.dp)
                                .background(Color(0x33FFFFFF), RoundedCornerShape(12.dp))
                                .padding(vertical = 12.dp, horizontal = 20.dp)
                        )
                    }


                    Button(
                        onClick = { carritoState.clear() },
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
