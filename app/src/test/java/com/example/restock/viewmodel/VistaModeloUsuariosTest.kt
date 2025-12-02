package com.example.restock.viewmodel

import org.junit.Assert.*
import org.junit.Test


data class UsuarioFake(
    val nombre: String,
    val correo: String,
    val password: String
)


class VistaModeloUsuariosFake {

    val usuarios = mutableListOf<UsuarioFake>()

    fun agregar(nombre: String, correo: String, pass: String) {
        usuarios.add(UsuarioFake(nombre, correo, pass))
    }

    fun eliminar(correo: String) {
        usuarios.removeIf { it.correo == correo }
    }
}

class VistaModeloUsuariosTest {

    @Test
    fun agregarUsuario_incrementaLista() {
        val vm = VistaModeloUsuariosFake()
        vm.agregar("Juan", "j@j.com", "123")

        assertEquals(1, vm.usuarios.size)
        assertEquals("Juan", vm.usuarios[0].nombre)
    }

    @Test
    fun eliminarUsuario_loQuitaCorrectamente() {
        val vm = VistaModeloUsuariosFake()
        vm.agregar("Juan", "j@j.com", "123")
        vm.agregar("Ana", "a@a.com", "456")

        vm.eliminar("j@j.com")

        assertEquals(1, vm.usuarios.size)
        assertEquals("a@a.com", vm.usuarios[0].correo)
    }
}
