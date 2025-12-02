package com.milsabores.pasteleria.network

import retrofit2.http.GET

data class PromoApiItem(
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

interface ApiExternaService {
    @GET("products?limit=2")
    suspend fun obtenerPromosApi(): List<PromoApiItem>
}
