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
            nombre = "Torta Red Velvet",
            descripcion = "Bizcocho rojo con crema suave",
            imagenResId = R.drawable.torta_red_velvet,
            tipo = TipoTorta.CUADRADA
        ),
        Producto(
            id = 4,
            nombre = "Torta de Vainilla",
            descripcion = "Torta clásica con suave sabor a vainilla",
            imagenResId = R.drawable.torta_vainilla,
            tipo = TipoTorta.CIRCULAR
        )
    )

    fun getPromociones(): List<Promocion> = listOf(
        Promocion(
            id = 1,
            titulo = "Promoción Torta de Chocolate",
            descripcion = "Descuento del 25% en nuestra torta especial",
            imagenResId = R.drawable.promocion_chocolate,
            precioOriginal = 30000,
            precioOferta = 22500,
            codigo = "CHOCO25",
            validezHasta = "31 de Enero 2025"
        ),
        Promocion(
            id = 2,
            titulo = "Promoción Bodas",
            descripcion = "Oferta especial para tortas de bodas",
            imagenResId = R.drawable.promocion_bodas,
            precioOriginal = 120000,
            precioOferta = 100000,
            codigo = "BODAS20",
            validezHasta = "31 de Diciembre 2025"
        ),
        Promocion(
            id = 3,
            titulo = "Combo Familiar",
            descripcion = "2 tortas medianas por el precio de 1 grande",
            imagenResId = R.drawable.promocion_familiar,
            precioOriginal = 35000,
            precioOferta = 35000,
            codigo = "COMBO2X1",
            validezHasta = "29 de Febrero 2025"
        )
    )
}



