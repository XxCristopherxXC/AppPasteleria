package com.milsabores.pasteleria.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class UsuariosViewModelTest {

    private lateinit var viewModel: UsuariosViewModel

    @Before
    fun setup() {
        viewModel = UsuariosViewModel()
    }

    @Test
    fun `crearCuenta asigna correctamente el usuario`() {
        viewModel.crearCuenta(
            nombre = "Juan",
            apellido = "Pérez",
            correo = "juan@test.com",
            contrasena = "123456"
        )

        val usuario = viewModel.usuario.value

        assertThat(usuario).isNotNull()
        assertThat(usuario?.nombre).isEqualTo("Juan")
        assertThat(usuario?.apellido).isEqualTo("Pérez")
        assertThat(usuario?.correo).isEqualTo("juan@test.com")
        assertThat(usuario?.contrasena).isEqualTo("123456")
    }

    @Test
    fun `cerrarSesion deja usuario en null`() {
        viewModel.crearCuenta("Juan", "Pérez", "jp@test.com", "123456")

        viewModel.cerrarSesion()

        assertThat(viewModel.usuario.value).isNull()
    }

    @Test
    fun `estaLogueado retorna true cuando existe usuario`() {
        viewModel.crearCuenta("Ana", "López", "ana@test.com", "123456")

        assertThat(viewModel.estaLogueado()).isTrue()
    }

    @Test
    fun `estaLogueado retorna false cuando no hay usuario`() {
        assertThat(viewModel.estaLogueado()).isFalse()
    }
}


