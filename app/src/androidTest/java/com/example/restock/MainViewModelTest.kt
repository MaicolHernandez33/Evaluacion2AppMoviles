package com.example.restock


import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.restock.viewmodel.MainViewModel
import com.example.restock.viewmodel.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    // Necesario para ejecutar LiveData / StateFlow en tests
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var app: Application
    private lateinit var vm: MainViewModel

    @Before
    fun setup() {
        app = Application()
        vm = MainViewModel(app)
    }

    // ---------------------------------------------------------
    // TEST 1 — Estado inicial cambia según LocalStorage
    // ---------------------------------------------------------
    @Test
    fun estadoInicial_correcto() = runTest {
        val estado = vm.ui.value.screen
        assertTrue(estado == Screen.LOGIN || estado == Screen.HOME)
    }

    // ---------------------------------------------------------
    // TEST 2 — goTo cambia pantalla
    // ---------------------------------------------------------
    @Test
    fun goTo_cambiaPantalla() = runTest {
        vm.goTo(Screen.CATALOGO)
        assertEquals(Screen.CATALOGO, vm.ui.value.screen)
    }

    // ---------------------------------------------------------
    // TEST 3 — goTo limpia mensaje
    // ---------------------------------------------------------
    @Test
    fun goTo_limpiaMensaje() = runTest {
        vm.goTo(Screen.HOME)
        vm.goTo(Screen.CARRITO)
        assertNull(vm.ui.value.message)
    }

    // ---------------------------------------------------------
    // TEST 4 — Registro exitoso va a HOME y guarda nombre
    // ---------------------------------------------------------
    @Test
    fun register_registroCorrecto() = runTest {
        vm.register("Maicol", "maicol@test.com", "1234")

        assertEquals(Screen.HOME, vm.ui.value.screen)
        assertEquals("Maicol", vm.ui.value.userName)
    }

    // ---------------------------------------------------------
    // TEST 5 — Login correcto avanza a HOME
    // ---------------------------------------------------------
    @Test
    fun login_correcto() = runTest {
        vm.register("Maik", "test@test.com", "1234")

        vm.login("test@test.com", "1234")

        assertEquals(Screen.HOME, vm.ui.value.screen)
    }

    // ---------------------------------------------------------
    // TEST 6 — Login incorrecto muestra error
    // ---------------------------------------------------------
    @Test
    fun login_incorrecto_mensajeError() = runTest {
        vm.login("correo@noexiste.com", "1234")

        assertEquals("Correo o contraseña incorrectos.", vm.ui.value.message)
    }

    // ---------------------------------------------------------
    // TEST 7 — Logout vuelve al LOGIN
    // ---------------------------------------------------------
    @Test
    fun logout_vuelveALogin() = runTest {
        vm.logout()

        assertEquals(Screen.LOGIN, vm.ui.value.screen)
        assertNull(vm.ui.value.userName)
    }

    // ---------------------------------------------------------
    // TEST 8 — isLoading vuelve a false tras login
    // ---------------------------------------------------------
    @Test
    fun login_activaLoading() = runTest {
        vm.login("x@x.com", "123")

        assertFalse(vm.ui.value.isLoading)
    }

    // ---------------------------------------------------------
    // TEST 9 — Carrito: agregar item
    // ---------------------------------------------------------
    @Test
    fun carrito_agregarItem() {
        vm.carrito.add(Pair(1, "Coca Cola"))

        assertEquals(1, vm.carrito.size)
    }

    // ---------------------------------------------------------
    // TEST 10 — Carrito: eliminar item
    // ---------------------------------------------------------
    @Test
    fun carrito_eliminarItem() {
        vm.carrito.add(Pair(1, "Coca Cola"))
        vm.carrito.removeAt(0)

        assertEquals(0, vm.carrito.size)
    }
}
