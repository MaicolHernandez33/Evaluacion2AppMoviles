package com.example.restock

object EmailValidator {

    fun esValido(email: String): Boolean {
        val t = email.trim()
        return t.contains("@") && t.contains(".") && t.indexOf("@") < t.lastIndexOf(".")
    }

    fun obtenerDominio(email: String): String {
        return if (esValido(email)) {
            email.substringAfter("@")
        } else {
            "invalido"
        }
    }
}
