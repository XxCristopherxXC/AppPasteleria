package com.milsabores.pasteleria.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientProductos {

    private const val BASE_URL = "http://10.0.2.2:8081/"   // Microservicio Productos - Puerto corregido a 8081

    val api: ProductosApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductosApi::class.java)
}
