package com.example.restock.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restock.ui.theme.*


@Composable
fun CarritoScreen(
    carrito: MutableList<Pair<Int, String>>,
    onBack: () -> Unit
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(fondo)
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onBack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                )
                ) { Text("← Volver") }
            Text("Carrito", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        if (carrito.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carrito.size) { index ->
                    val (imagen, nombre) = carrito[index]
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(imagen),
                                contentDescription = nombre,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(4.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(nombre, fontWeight = FontWeight.Bold)
                        }
                        Button(onClick = { carrito.removeAt(index) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = naranjo,
                                contentColor = Color.White
                            )
                            ) {
                            Text("Quitar")
                        }
                    }
                }
            }

            Button(
                onClick = { carrito.clear() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Vaciar carrito")
            }
        }
    }
}
