package com.milsabores.pasteleria.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET("promotions")
    suspend fun obtenerPromos(): List<PromoApiItem>
}
