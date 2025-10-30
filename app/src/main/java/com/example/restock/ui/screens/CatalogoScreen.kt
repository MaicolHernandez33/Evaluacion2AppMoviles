package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.restock.R
import com.example.restock.ui.theme.*


@Composable
fun CatalogoScreen(
    onBack: () -> Unit,
    onGoToCarrito: () -> Unit,
    carrito: MutableList<Pair<Int, String>>
) {
    val productos = listOf(
    Pair(R.drawable.combo1, "Combo 1 - $10.500"),
    Pair(R.drawable.combo2, "Combo 2 - $12.000"),
    Pair(R.drawable.combo3, "Combo 3 - $15.000"),
    Pair(R.drawable.combo4, "Combo 4 - $8.000"),
    Pair(R.drawable.combo5, "Combo 5 - $9.500")
)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(fondo)
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // titulo
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(16.dp),
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

            Spacer(Modifier.width(12.dp))
            Text("Catálogo", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Button(onClick = { onGoToCarrito() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                )
                ) {
                Text("Ver carrito (${carrito.size})")
            }
        }

        //mostrar de productos
        LazyColumn(modifier = Modifier.fillMaxSize()

        ) {
            items(productos.size) { index ->
                val (imagen, nombre) = productos[index]
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = naranjo)

                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(imagen),
                                contentDescription = nombre,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(4.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(nombre, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Button(onClick = { carrito.add(productos[index]) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = naranjo
                            )

                            ) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}