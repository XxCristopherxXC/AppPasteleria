package com.milsabores.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import com.milsabores.pasteleria.data.models.ItemCarrito
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Tamano
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {

    private val _carritoItems = MutableStateFlow<List<ItemCarrito>>(emptyList())
    val carritoItems: StateFlow<List<ItemCarrito>> = _carritoItems

    // Agrega un producto al carrito o incrementa cantidad si ya existe
    fun agregarProducto(producto: Producto, tamano: Tamano = Tamano.INTERMEDIA) {
        val current = _carritoItems.value.toMutableList()
        val existing = current.find { it.producto.id == producto.id && it.tamano == tamano }
        if (existing != null) {
            existing.cantidad += 1
        } else {
            current.add(ItemCarrito(producto = producto, tamano = tamano))
        }
        _carritoItems.value = current
    }

    fun vaciarCarrito() {
        _carritoItems.value = emptyList()
    }
}
