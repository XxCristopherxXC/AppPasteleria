package com.milsabores.pasteleria.data.models

data class ItemCarrito(
    val producto: Producto,
    var cantidad: Int = 1,
    val tamano: Tamano = Tamano.INTERMEDIA,
    val mensaje: String = ""
) {
    fun calcularSubtotal(): Int {
        val precioBase = when (tamano) {
            Tamano.PEQUENA -> 15000
            Tamano.INTERMEDIA -> 25000
            Tamano.GIGANTE -> 35000
        }
        return precioBase * cantidad
    }

    val id: Long
        get() = producto.id
}

