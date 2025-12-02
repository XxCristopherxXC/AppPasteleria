package com.example.app_pasteleria.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientCarrito {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8082/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CarritoApi = retrofit.create(CarritoApi::class.java)
}

