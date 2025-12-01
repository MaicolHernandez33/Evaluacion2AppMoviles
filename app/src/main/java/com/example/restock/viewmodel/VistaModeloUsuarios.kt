package com.example.restock.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.restock.data.BaseDatosHelper
import com.example.restock.model.UsuarioSQLite

class VistaModeloUsuarios(context: Context) : ViewModel() {

    private val db = BaseDatosHelper(context)

    var usuarios = mutableStateListOf<UsuarioSQLite>()
        private set

    fun cargar() {
        usuarios.clear()
        usuarios.addAll(db.listar())
    }

    fun agregar(nombre: String, correo: String, pass: String) {
        db.insertar(nombre, correo, pass)
        cargar()
    }

    fun eliminar(correo: String) {
        db.eliminar(correo)
        cargar()
    }
}
