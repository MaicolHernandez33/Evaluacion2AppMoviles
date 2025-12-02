package com.example.restock.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.restock.model.UsuarioSQLite

private const val DB_NAME = "usuarios.db"
private const val DB_VERSION = 4   // ← SUBE LA VERSIÓN PARA ELIMINAR EL ERROR

class BaseDatosHelper(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS usuario (
                nombre TEXT,
                correo TEXT PRIMARY KEY,
                password TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        // Aplicar cambios si hay nuevas versiones (por ahora no hacemos nada)
        // IMPORTANTE: No borrar datos ni tablas.
        if (oldV < 4) {
            // Si más adelante agregas columnas, puedes hacerlo aquí
            // Ejemplo:
            // db.execSQL("ALTER TABLE usuario ADD COLUMN telefono TEXT DEFAULT ''")
        }
    }

    fun insertar(nombre: String, correo: String, password: String) {
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("correo", correo)
            put("password", password)
        }
        writableDatabase.insert("usuario", null, valores)
    }

    fun listar(): List<UsuarioSQLite> {
        val lista = mutableListOf<UsuarioSQLite>()
        val cursor = readableDatabase.rawQuery(
            "SELECT nombre, correo, password FROM usuario",
            null
        )
        while (cursor.moveToNext()) {
            lista.add(
                UsuarioSQLite(
                    nombre = cursor.getString(0),
                    correo = cursor.getString(1),
                    password = cursor.getString(2)
                )
            )
        }
        cursor.close()
        return lista
    }

    fun eliminar(correo: String) {
        writableDatabase.delete("usuario", "correo=?", arrayOf(correo))
    }

    fun actualizar(nombre: String, correo: String, password: String) {
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("password", password)
        }
        writableDatabase.update(
            "usuario",
            valores,
            "correo=?",
            arrayOf(correo)
        )
    }
}
