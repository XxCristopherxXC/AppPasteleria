package com.milsabores.pasteleria.data.models

data class Promocion(
    val id: Long,
    val titulo: String,
    val descripcion: String,
    val imagenResId: Int,
    val precioOriginal: Int,
    val precioOferta: Int,
    val codigo: String,
    val validezHasta: String
)
