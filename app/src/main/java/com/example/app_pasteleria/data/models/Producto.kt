package com.milsabores.pasteleria.data.models

import com.milsabores.pasteleria.R

data class Producto(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val tipo: TipoTorta,
    val preciosPorTamano: Map<Tamano, Int> = mapOf(
        Tamano.PEQUENA to 15000,
        Tamano.INTERMEDIA to 25000,
        Tamano.GIGANTE to 35000
    )
)

