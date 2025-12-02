package com.milsabores.pasteleria.network

data class PedidoRequest(
    val idCliente: Long,
    val nombreCliente: String,
    val apellidoCliente: String,
    val fecha: String,
    val total: Int,
    val items: List<ItemPedidoRequest>
)

data class ItemPedidoRequest(
    val productoId: String,
    val nombre: String,
    val cantidad: Int,
    val precio: Int
)
