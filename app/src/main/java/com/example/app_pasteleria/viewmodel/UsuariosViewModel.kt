package com.milsabores.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Usuario(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String
)

class UsuariosViewModel : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    private val _apellido = MutableStateFlow("")
    val apellido: StateFlow<String> = _apellido

    private val _correo = MutableStateFlow("")
    val correo: StateFlow<String> = _correo

    private val _contrasena = MutableStateFlow("")
    val contrasena: StateFlow<String> = _contrasena

    fun actualizarNombre(value: String) { _nombre.value = value }
    fun actualizarApellido(value: String) { _apellido.value = value }
    fun actualizarCorreo(value: String) { _correo.value = value }
    fun actualizarContrasena(value: String) { _contrasena.value = value }

    fun validarCampos(): Boolean {
        return _nombre.value.isNotBlank() &&
                _apellido.value.isNotBlank() &&
                _correo.value.contains("@") &&
                _contrasena.value.length >= 6
    }

    fun crearCuenta(nombre: String, apellido: String, correo: String, contrasena: String) {
        _usuario.value = Usuario(nombre, apellido, correo, contrasena)
    }

    fun cerrarSesion() {
        _usuario.value = null
    }

    fun estaLogueado(): Boolean = _usuario.value != null
}