package com.milsabores.pasteleria.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientClientes {

    private const val BASE_URL = "http://10.0.2.2:8081/"   // Microservicio Clientes

    val api: ClientesApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientesApi::class.java)
}
