package com.example.restock

import org.junit.Assert
import org.junit.Test

class ValidacionesTest {

    fun esEmailValido(email: String): Boolean =
        email.contains("@") && email.contains(".")

    fun passValida(pass: String): Boolean =
        pass.length >= 6

    @Test
    fun emailValido_retornaTrue() {
        Assert.assertTrue(esEmailValido("test@correo.com"))
    }

    @Test
    fun passCorta_retornaFalse() {
        Assert.assertFalse(passValida("123"))
    }
}