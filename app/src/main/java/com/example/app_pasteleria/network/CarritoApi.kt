package com.example.app_pasteleria.network

import retrofit2.http.*

interface CarritoApi {

    @GET("carrito/{idCliente}")
    suspend fun obtenerCarrito(@Path("idCliente") idCliente: Long): List<CarritoItemResponse>

    @POST("carrito")
    suspend fun agregarAlCarrito(@Body item: CarritoItemRequest): CarritoItemResponse

    @DELETE("carrito/{idCliente}")
    suspend fun vaciarCarrito(@Path("idCliente") idCliente: Long)

    @DELETE("carrito/item/{idItem}")
    suspend fun eliminarDelCarrito(@Path("idItem") idItem: Long)
}



