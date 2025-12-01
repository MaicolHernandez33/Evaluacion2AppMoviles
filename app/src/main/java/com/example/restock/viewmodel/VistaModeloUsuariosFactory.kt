package com.example.restock.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VistaModeloUsuariosFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VistaModeloUsuarios::class.java)) {
            return VistaModeloUsuarios(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
