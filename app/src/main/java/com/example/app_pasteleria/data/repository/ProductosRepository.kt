package com.milsabores.pasteleria.data.repository

import com.milsabores.pasteleria.R
import com.milsabores.pasteleria.data.models.*

object ProductosRepository {

    fun getProductos(): List<Producto> = listOf(
        Producto(
            id = 1,
            nombre = "Torta Cuadrada de Chocolate",
            descripcion = "Pastel de chocolate con relleno cremoso",
            imagenResId = R.drawable.torta_chocolate,
            tipo = TipoTorta.CUADRADA
        ),
        Producto(
            id = 2,
            nombre = "Torta Circular de Fresa",
            descripcion = "Bizcocho esponjoso con fresas",
            imagenResId = R.drawable.torta_fresa,
            tipo = TipoTorta.CIRCULAR
        ),
        Producto(
            id = 3,
            nombre = "Torta Chocolate Doble",
            descripcion = "Chocolate intenso con relleno extra",
            imagenResId = R.drawable.torta_chocolate, // reutiliza la misma imagen
            tipo = TipoTorta.CUADRADA
        ),
        Producto(
            id = 4,
            nombre = "Torta Fresa Especial",
            descripcion = "Bizcocho de fresa con crema especial",
            imagenResId = R.drawable.torta_fresa, // reutiliza la misma imagen
            tipo = TipoTorta.CIRCULAR
        )
    )

    fun getPromociones(): List<Promocion> = listOf(
        Promocion(
            id = 1,
            titulo = "Torta de Chocolate Especial",
            descripcion = "Descuento del 25%",
            imagenResId = R.drawable.promocion_chocolate,
            precioOriginal = 30000,
            precioOferta = 22500,
            codigo = "CHOCO25",
            validezHasta = "31 de Enero 2025"
        )
    )
}
//creacion de repository crea fallas arreglarlo cris 

