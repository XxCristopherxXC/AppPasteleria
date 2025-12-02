package com.milsabores.pasteleria.network

import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {
    @POST("/pedidos/crear")
    suspend fun crearPedido(@Body req: PedidoRequest): PedidoResponse
}