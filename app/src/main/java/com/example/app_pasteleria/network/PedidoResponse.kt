package com.milsabores.pasteleria.network

data class PedidoResponse(
    val idPedido: Long,
    val fecha: String,
    val total: Int,
    val idCliente: Long
)
