package com.milsabores.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CrearCuentaViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var errorMensaje by mutableStateOf<String?>(null)

    fun validarCampos(): Boolean {
        return when {
            nombre.isBlank() || apellido.isBlank() || correo.isBlank() || contrasena.isBlank() -> {
                errorMensaje = "Completa todos los campos"
                false
            }
            !correo.contains("@") -> {
                errorMensaje = "Correo no válido"
                false
            }
            contrasena.length < 6 -> {
                errorMensaje = "La contraseña debe tener al menos 6 caracteres"
                false
            }
            else -> {
                errorMensaje = null
                true
            }
        }
    }
}
