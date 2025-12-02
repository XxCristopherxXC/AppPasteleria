package com.example.app_pasteleria.network

data class CarritoItemRequest(
    val idCliente: Long,
    val idProducto: Long,
    val cantidad: Int,
    val tamano: String
)
