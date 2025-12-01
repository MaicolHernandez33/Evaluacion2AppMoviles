package com.example.restock.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.restock.R
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.naranjo

@Composable
fun HomeScreen(
    onGoToCatalogo: () -> Unit = {},
    onGoToNosotros: () -> Unit = {},
    onGoToContacto: () -> Unit = {},
    onGoToProfile: () -> Unit = {},
    onGoToQrScreen: () -> Unit = {},
    onGoToCRUDUsuarios: () -> Unit = {},
    onGoToClima: () -> Unit = {},
    onLogout: () -> Unit = {}
) {

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
                    .size(160.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
            )

            Surface(
                color = Color.Black.copy(alpha = 0.45f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "Bienvenido a Restock",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                )
            }



            Spacer(Modifier.height(25.dp))

            // --- GRID DE BOTONES EN 2 COLUMNAS ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    HomeItem("Catálogo", Icons.Default.MenuBook, onGoToCatalogo)
                    HomeItem("Nosotros", Icons.Default.Info, onGoToNosotros)
                }

                Spacer(Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    HomeItem("Contacto", Icons.Default.Phone, onGoToContacto)
                    HomeItem("Mi Perfil", Icons.Default.Person, onGoToProfile)
                }

                Spacer(Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    HomeItem("Escanear", Icons.Default.QrCodeScanner, onGoToQrScreen)
                    HomeItem("Clima", Icons.Default.Cloud, onGoToClima)
                }

                Spacer(Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    HomeItem("Usuarios", Icons.Default.Group, onGoToCRUDUsuarios)
                    HomeItem("Salir", Icons.Default.ExitToApp, onLogout)
                }
            }
        }
    }
}

@Composable
fun HomeItem(texto: String, icono: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = icono,
                contentDescription = texto,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                texto,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
