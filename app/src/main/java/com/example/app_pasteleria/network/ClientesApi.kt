package com.milsabores.pasteleria.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ClientesApi {
    @GET("api/v1/clientes/{id}")
    suspend fun getCliente(@Path("id") id: Long): ClienteResponse
}
