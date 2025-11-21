package com.example.restock.ui.screens

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.restock.ui.theme.naranjo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restock.ui.components.CommonBackground
import com.example.restock.ui.theme.RestockTheme
import com.example.restock.ui.theme.naranjo
import com.example.restock.viewmodel.MainViewModel
import com.example.restock.viewmodel.Screen

@Composable
fun MainScreen(
    urlText:String,
    onUrlTextUpdate: (String) -> Unit) {
    val vm: MainViewModel = viewModel()

    RestockTheme () {
        QrScannerScreen (onBack = { vm.goTo(Screen.HOME) },urlText, onUrlTextUpdate)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(urlText = "", onUrlTextUpdate = {})
}
const val URL_KEY: String = "UrlKey"
private var urlText by mutableStateOf("")
private fun launchUrl(context: Context, urlText: String) {
    val uri: Uri = Uri.parse(urlText)

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.android.chrome")
    }

    try {
        context.startActivity(intent)

    } catch (e: ActivityNotFoundException) {
        intent.setPackage(null)

        try {
            context.startActivity(intent)

        } catch (e: ActivityNotFoundException) {

        }
    }
}
@Composable
fun QrScannerScreen(
    onBack: () -> Unit,
    urlText: String,
    onUrlTextUpdate: (String) -> Unit
) {
    var statusText by remember { mutableStateOf("") }
    val context = LocalContext.current



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
                androidx.compose.material3.Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) { Text("Volver") }

                Spacer(Modifier.width(12.dp))
                Text(
                    "Escanee el QR en el Restaurante",
                    fontWeight = FontWeight.Bold,
                    color = Color.White // ← Color blanco para contraste con el fondo
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = statusText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp
                )

                Spacer(Modifier.height(5.dp))

                CamaraPreview { url ->
                    onUrlTextUpdate(url)
                }

                Box(Modifier.background(Color.White)){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = urlText,
                    onValueChange = {},
                    label = { Text("URL detectada") },
                    readOnly = true,
                )}

                Spacer(Modifier.height(5.dp))

                androidx.compose.material3.Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { launchUrl(context, urlText) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = naranjo, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) { Text( "Escanee QR",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp)
                }
                }
            }
    }

    PermisoCamara(
        permission = Manifest.permission.CAMERA,
        onResult = { isGranted ->
            statusText = if (isGranted) {
                "Escanee QR"
            } else {
                "No hay acceso a cámara"
            }
        },
    )


}












