package com.milsabores.pasteleria.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.app_pasteleria.network.ApiClientCarrito
import com.example.app_pasteleria.network.CarritoItemRequest
import com.example.app_pasteleria.network.CarritoItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {

    private val api = ApiClientCarrito.api

    private val _carrito = MutableStateFlow<List<CarritoItemResponse>>(emptyList())
    val carrito: StateFlow<List<CarritoItemResponse>> get() = _carrito

    val idCliente = 1L

    // Cargar carrito
    suspend fun cargarCarrito() {
        try {
            Log.d("CarritoViewModel", "Cargando carrito para cliente: $idCliente")
            _carrito.value = api.obtenerCarrito(idCliente)
            Log.d("CarritoViewModel", "Carrito cargado: ${_carrito.value.size} items")
        } catch (e: Exception) {
            Log.e("CarritoViewModel", "Error al cargar carrito: ${e.message}", e)
            e.printStackTrace()
        }
    }

    // Agregar o actualizar cantidad
    suspend fun agregar(productoId: Long, cantidad: Int, tamano: String) {
        try {
            api.agregarAlCarrito(
                CarritoItemRequest(
                    idCliente = idCliente,
                    idProducto = productoId,
                    cantidad = cantidad,
                    tamano = tamano
                )
            )
            cargarCarrito()
        } catch (e: Exception) {
            throw Exception("No se pudo agregar: ${e.message}")
        }
    }

    // ELIMINAR ITEM POR ID
    suspend fun eliminar(idItem: Long) {
        try {
            Log.d("CarritoViewModel", "Eliminando item con ID: $idItem")
            api.eliminarDelCarrito(idItem)
            Log.d("CarritoViewModel", "Item eliminado exitosamente")
            cargarCarrito()
        } catch (e: Exception) {
            Log.e("CarritoViewModel", "Error al eliminar item: ${e.message}", e)
            e.printStackTrace()
            throw e
        }
    }

    // Vaciar carrito completo
    suspend fun vaciar() {
        try {
            Log.d("CarritoViewModel", "Vaciando carrito del cliente: $idCliente")
            api.vaciarCarrito(idCliente)
            Log.d("CarritoViewModel", "Carrito vaciado exitosamente")
            cargarCarrito()
        } catch (e: Exception) {
            Log.e("CarritoViewModel", "Error al vaciar carrito: ${e.message}", e)
            e.printStackTrace()
            throw e
        }
    }
}

