package com.milsabores.pasteleria.network

import retrofit2.http.GET

interface ProductosApi {
    @GET("api/v1/productos")
    suspend fun obtenerProductos(): List<ProductoResponse>
}