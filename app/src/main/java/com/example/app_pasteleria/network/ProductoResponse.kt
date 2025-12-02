package com.milsabores.pasteleria.network

data class ProductoResponse(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val imagenUrl: String
)
