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

    fun crearCuenta(nombre: String, apellido: String, correo: String, contrasena: String) {
        _usuario.value = Usuario(nombre, apellido, correo, contrasena)
    }

    fun cerrarSesion() {
        _usuario.value = null
    }

    fun estaLogueado(): Boolean = _usuario.value != null
}
