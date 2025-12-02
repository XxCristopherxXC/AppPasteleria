package com.milsabores.pasteleria.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiExternaClient {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiExternaService = retrofit.create(ApiExternaService::class.java)
}
