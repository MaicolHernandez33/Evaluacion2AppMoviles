package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.naranjo
import com.example.restock.viewmodel.ClimaViewModel

@Composable
fun ClimaScreen(onBack: () -> Unit) {

    val vm: ClimaViewModel = viewModel()
    val ui by vm.ui.collectAsState()

    val lat = -41.4728
    val lon = -72.9420

    LaunchedEffect(true) {
        vm.cargarClima(lat, lon)
    }

    CommonBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo Restock",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 10.dp)
            )

            // TITULO
            Text(
                text = "Clima en Puerto Montt",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                "Restock",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(Modifier.height(20.dp))

            if (ui.cargando) {
                CircularProgressIndicator(color = naranjo)
            }

            ui.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            if (ui.temp != null) {

                // Color dinámico según estado del clima
                val cardColor = when {
                    ui.descripcion?.contains("Lluvia") == true -> MaterialTheme.colorScheme.secondaryContainer
                    ui.descripcion?.contains("Despejado") == true -> MaterialTheme.colorScheme.tertiaryContainer
                    else -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "🌡️ Temperatura: ${ui.temp}°C",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text("🔥 Sensación: ${"%.1f".format(ui.sensacion)}°C")
                        Text("💨 Viento: ${ui.viento} km/h")
                        Text("⛅ Estado: ${ui.descripcion}")

                        Spacer(Modifier.height(10.dp))

                        Text(
                            "⏱️ Actualizado a las ${ui.ultimaHora}",
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(40.dp))

            // BOTÓN VOLVER
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo
                ),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Volver")
            }
        }
    }
}
