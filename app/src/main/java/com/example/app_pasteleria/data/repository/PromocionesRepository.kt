package com.milsabores.pasteleria.data.remote

class PromocionesRepository {

    suspend fun cargarPromosApi(): List<PromoApiItem> {
        return RetrofitClient.api.obtenerPromos()
    }
}
