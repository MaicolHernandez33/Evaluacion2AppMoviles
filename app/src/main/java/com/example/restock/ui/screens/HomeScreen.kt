package com.example.restock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restock.R
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.*

@Composable
fun HomeScreen(
    onGoToCatalogo: () -> Unit = {},
    onGoToNosotros: () -> Unit = {},
    onGoToContacto: () -> Unit = {},
    onGoToProfile: () -> Unit = {},
    onGoToQrScreen: () -> Unit = {},
    onGoToCRUDUsuarios: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    CommonBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logotipo",
                modifier = Modifier
                    .height(180.dp)
                    .padding(bottom = 32.dp)
            )

            Text(
                text = "¡Bienvenido a Restock!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botones principales
            Button(
                onClick = onGoToCatalogo,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Catálogo")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = onGoToNosotros,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nosotros")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = onGoToContacto,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Contacto")
            }

            Spacer(Modifier.height(16.dp))

            // PERFIL
            Button(
                onClick = onGoToProfile,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mi Perfil")
            }

            Spacer(Modifier.height(16.dp))

            // QR
            Button(
                onClick = onGoToQrScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Escanear código en restaurante")
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = onGoToCRUDUsuarios,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Administrar Usuarios")
            }

            Spacer(Modifier.height(32.dp))

            // Botón de cerrar sesión
            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjo,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}