package com.milsabores.pasteleria.network

import retrofit2.http.Body
import retrofit2.http.POST

interface PedidosApi {
    @POST("api/v1/pedidos")
    suspend fun crearPedido(@Body request: PedidoRequest): PedidoResponse
}

