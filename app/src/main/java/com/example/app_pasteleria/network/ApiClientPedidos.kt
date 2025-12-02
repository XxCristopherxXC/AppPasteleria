package com.milsabores.pasteleria.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientPedidos {

    private const val BASE_URL = "http://10.0.2.2:8082/"   // Microservicio Pedidos

    val api: PedidosApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PedidosApi::class.java)
}

