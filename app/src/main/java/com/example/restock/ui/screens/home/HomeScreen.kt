package com.example.restock.ui.screens.home

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAAC8B))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logotipo",
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 32.dp)
        )
        Text("¡Bienvenido a Restock!")

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onGoToCatalogo,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE7651),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Catálogo") }

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onGoToNosotros,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8572A),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Nosotros") }

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onGoToContacto,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE7651),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Contacto") }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8572A),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Cerrar sesión") }
    }
}
