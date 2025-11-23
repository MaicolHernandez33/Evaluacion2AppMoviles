package com.example.restock.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.restock.model.UsuarioSQLite

class BaseDatosHelper(context: Context)
    : SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE usuario (
                nombre TEXT,
                correo TEXT PRIMARY KEY,
                password TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        onCreate(db)
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
