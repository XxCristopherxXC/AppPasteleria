package com.milsabores.pasteleria.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsuariosViewModelTest {

    private lateinit var viewModel: UsuariosViewModel

    @Before
    fun setup() {
        viewModel = UsuariosViewModel()
    }

    @Test
    fun `actualizarNombre actualiza correctamente`() = runTest {
        viewModel.actualizarNombre("Juan")

        viewModel.nombre.test {
            val value = awaitItem()
            assertThat(value).isEqualTo("Juan")
        }
    }

    @Test
    fun `validarCampos retorna true si datos correctos`() = runTest {
        viewModel.actualizarNombre("Juan")
        viewModel.actualizarApellido("Perez")
        viewModel.actualizarCorreo("correo@ejemplo.com")
        viewModel.actualizarContrasena("123456")

        val result = viewModel.validarCampos()
        assertThat(result).isTrue()
    }

    @Test
    fun `validarCampos retorna false si datos incorrectos`() = runTest {
        viewModel.actualizarNombre("")
        viewModel.actualizarApellido("")
        viewModel.actualizarCorreo("correoInvalido")
        viewModel.actualizarContrasena("123")

        val result = viewModel.validarCampos()
        assertThat(result).isFalse()
    }
}


