package com.milsabores.pasteleria.network

data class ClienteResponse(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val direccion: String,
    val rut: Long
)
