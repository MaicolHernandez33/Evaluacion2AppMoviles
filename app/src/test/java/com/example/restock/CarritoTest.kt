package com.example.restock

import com.example.restock.model.Producto
import org.junit.Assert.*
import org.junit.Test

class CarritoTest {

    @Test
    fun totalCarrito_seCalculaCorrectamente() {
        val carrito = listOf(
            Producto(1, "Café", 1500.0, ""),
            Producto(2, "Té", 1000.0, "")
        )

        val total = carrito.sumOf { it.precio }
        assertEquals(2500.0, total, 0.0)
    }

    @Test
    fun quitarProducto_funciona() {
        val carrito = mutableListOf(
            Producto(1, "Café", 1500.0, ""),
            Producto(2, "Té", 1000.0, "")
        )

        carrito.removeAt(0)
        assertEquals(1, carrito.size)
    }
}
