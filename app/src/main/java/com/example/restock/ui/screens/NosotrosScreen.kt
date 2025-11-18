package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground // ← NUEVO IMPORT
import com.example.restock.ui.theme.*

@Composable
fun NosotrosScreen(
    onBack: () -> Unit = {}
) {

    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top bar - Fijo en la parte superior
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) { Text("Volver") }

                Spacer(Modifier.width(12.dp))
                Text(
                    "Nosotros",
                    fontWeight = FontWeight.Bold,
                    color = Color.White // ← Color blanco para contraste con el fondo
                )
            }

            // Contenedor centrado para el contenido
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {

                        // Logo centrado
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo Restock",
                                modifier = Modifier
                                    .height(90.dp)
                                    .padding(bottom = 16.dp)
                            )
                        }

                        Text(
                            "Restock — Restaurante Puerto Montt",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Cocinamos con productos frescos de la zona para ofrecer platos caseros, ricos y a precio justo. " +
                                    "Somos un equipo local que busca una experiencia cercana y rápida para nuestros clientes.",
                            color = Color(0xFF555555),
                            textAlign = TextAlign.Justify
                        )

                        Spacer(Modifier.height(16.dp))
                        Text("Horario", fontWeight = FontWeight.SemiBold)
                        Text(
                            "Lun–Vie: 12:30–22:00\nSáb–Dom: 12:30–23:30",
                            color = Color(0xFF555555)
                        )

                        Spacer(Modifier.height(12.dp))
                        Text("Dirección", fontWeight = FontWeight.SemiBold)
                        Text(
                            "Av. Costanera 1234, Puerto Montt",
                            color = Color(0xFF555555)
                        )

                        Spacer(Modifier.height(12.dp))
                        Text("Contacto", fontWeight = FontWeight.SemiBold)
                        Text(
                            "WhatsApp: +56 9 1234 5678\nInstagram: @restock_pm\nCorreo: hola@restock.cl",
                            color = Color(0xFF555555)
                        )
                    }
                }
            }
        }
    }
}