package com.example.restock.ui.screens

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.restock.QrAnalizador

@Composable
fun CamaraPreview(urlCallback: (String) -> Unit) {

    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    AndroidView(
        factory = { context ->
            val previewView = PreviewView(context)
            val preview = Preview.Builder().build()
            val cameraSelector = CameraSelector.Builder().build()

            preview.setSurfaceProvider(previewView.surfaceProvider)

            val imageAnalysis = ImageAnalysis.Builder().build()

            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                QrAnalizador { url ->
                    urlCallback(url)
                }
            )

            ProcessCameraProvider.getInstance(context).get().bindToLifecycle(
                lifeCycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            )

            previewView
        }
    )
}

