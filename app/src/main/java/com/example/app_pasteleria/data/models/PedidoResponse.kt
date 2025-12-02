package com.milsabores.pasteleria.data.models

data class PedidoResponse(
    val id: Int,
    val rutUsuario: Int,
    val total: Int,
    val mensaje: String? = null
)
