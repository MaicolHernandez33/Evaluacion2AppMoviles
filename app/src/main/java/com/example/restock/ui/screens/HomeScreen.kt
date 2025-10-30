package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.restock.R

@Composable
fun HomeScreen(
    onGoToCatalogo: () -> Unit = {},
    onGoToNosotros: () -> Unit = {},
    onGoToContacto: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val Orange = Color(0xFFD8572A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAAC8B))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logotipo",
            modifier = Modifier
                .height(180.dp)
                .padding(bottom = 16.dp)
        )

        Text("¡Bienvenido a Restock!")

        Spacer(Modifier.height(16.dp))

        // Botones principales (todos mismo color)
        Button(
            onClick = onGoToCatalogo,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Catálogo") }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = onGoToNosotros,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Nosotros") }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = onGoToContacto,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Contacto") }

        // Empuja el botón de logout hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Cerrar sesión más pequeño y al fondo
        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f) // más angosto
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp)
        ) { Text("Cerrar sesión") }
    }
}
