package com.example.restock.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.restock.R

@Composable
fun CommonBackground(
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo con opacidad reducida - AJUSTA ESTE VALOR
        Image(
            painter = painterResource(id = R.drawable.fondo_restaurante),
            contentDescription = "Fondo del restaurante Sabores del Puerto",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f), // Transpariencia
            contentScale = ContentScale.Crop
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            content()
        }
    }
}