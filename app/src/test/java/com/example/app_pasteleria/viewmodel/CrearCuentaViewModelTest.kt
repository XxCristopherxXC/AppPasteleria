package com.milsabores.pasteleria.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CrearCuentaViewModelTest {

    private lateinit var viewModel: CrearCuentaViewModel

    @Before
    fun setup() {
        viewModel = CrearCuentaViewModel()
    }

    @Test
    fun `validarCampos retorna false si hay campos vacios`() {
        viewModel.nombre = ""
        viewModel.apellido = ""
        viewModel.correo = ""
        viewModel.contrasena = ""

        val result = viewModel.validarCampos()
        assertThat(result).isFalse()
        assertThat(viewModel.errorMensaje).isEqualTo("Completa todos los campos")
    }

    @Test
    fun `validarCampos retorna false si correo invalido`() {
        viewModel.nombre = "Juan"
        viewModel.apellido = "Perez"
        viewModel.correo = "correoInvalido"
        viewModel.contrasena = "123456"

        val result = viewModel.validarCampos()
        assertThat(result).isFalse()
        assertThat(viewModel.errorMensaje).isEqualTo("Correo no válido")
    }

    @Test
    fun `validarCampos retorna false si contrasena corta`() {
        viewModel.nombre = "Juan"
        viewModel.apellido = "Perez"
        viewModel.correo = "correo@ejemplo.com"
        viewModel.contrasena = "123"

        val result = viewModel.validarCampos()
        assertThat(result).isFalse()
        assertThat(viewModel.errorMensaje).isEqualTo("La contraseña debe tener al menos 6 caracteres")
    }

    @Test
    fun `validarCampos retorna true si todo es correcto`() {
        viewModel.nombre = "Juan"
        viewModel.apellido = "Perez"
        viewModel.correo = "correo@ejemplo.com"
        viewModel.contrasena = "123456"

        val result = viewModel.validarCampos()
        assertThat(result).isTrue()
        assertThat(viewModel.errorMensaje).isNull()
    }
}

