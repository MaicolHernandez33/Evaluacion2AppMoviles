package com.example.restock.viewmodel

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test


// VIEWMODEL PARA TESTS

class TestableMainViewModel {

    var ui = UiState()

    fun login(email: String, pass: String) {
        ui = if (email == "admin@correo.cl" && pass == "123456") {
            ui.copy(screen = Screen.HOME, message = "Login OK")
        } else {
            ui.copy(message = "Error: credenciales inválidas")
        }
    }

    fun register(name: String, email: String, pass: String) {
        ui = ui.copy(screen = Screen.LOGIN, message = "Registro exitoso")
    }

    fun goTo(destino: Screen) {
        ui = ui.copy(screen = destino)
    }

    fun logout() {
        ui = ui.copy(screen = Screen.LOGIN, userName = null)
    }
}


// TESTS UNITARIOS

class MainViewModelTest {

    private val vm = TestableMainViewModel()

    @Test
    fun loginCorrecto_redirigeHome() = runTest {
        vm.login("admin@correo.cl", "123456")
        assertEquals(Screen.HOME, vm.ui.screen)
    }

    @Test
    fun loginIncorrecto_muestraError() = runTest {
        vm.login("invalido@correo.cl", "000")
        assertTrue(vm.ui.message!!.contains("Error", ignoreCase = true))
    }

    @Test
    fun register_redirigeALogin() = runTest {
        vm.register("Juan", "j@j.cl", "123456")
        assertEquals(Screen.LOGIN, vm.ui.screen)
    }

    @Test
    fun goTo_cambiaPantallaCorrectamente() {
        vm.goTo(Screen.CONTACTO)
        assertEquals(Screen.CONTACTO, vm.ui.screen)
    }

    @Test
    fun logout_vuelveALoginYLimpiaUsuario() {
        vm.logout()
        assertEquals(Screen.LOGIN, vm.ui.screen)
        assertNull(vm.ui.userName)
    }
}
