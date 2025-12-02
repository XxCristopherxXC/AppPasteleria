package com.milsabores.pasteleria.data.repository

import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Promocion
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ProductosRepositoryTest {

    @Test
    fun `getProductos retorna lista no vacia`() {
        val productos: List<Producto> = ProductosRepository.getProductos()
        assertThat(productos).isNotEmpty()
        assertThat(productos[0].nombre).isNotEmpty()
    }

    @Test
    fun `getPromociones retorna lista no vacia`() {
        val promociones: List<Promocion> = ProductosRepository.getPromociones()
        assertThat(promociones).isNotEmpty()
        assertThat(promociones[0].titulo).isNotEmpty()
        assertThat(promociones[0].codigo).isNotEmpty()
    }
}


