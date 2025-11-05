package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CrearCuentaScreen(
    onCuentaCreada: (String, String, String, String) -> Unit,
    onCancelarClick: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            isError = nombre.isBlank()
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            isError = apellido.isBlank()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = !correo.contains("@")
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = contrasena.length < 6
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    nombre.isBlank() || apellido.isBlank() ||
                            correo.isBlank() || contrasena.isBlank() -> {
                        errorMensaje = "Completa todos los campos"
                    }
                    !correo.contains("@") -> errorMensaje = "Correo no válido"
                    contrasena.length < 6 -> errorMensaje = "La contraseña debe tener al menos 6 caracteres"
                    else -> {
                        errorMensaje = null
                        onCuentaCreada(nombre, apellido, correo, contrasena)
                        scope.launch {
                            snackbarHostState.showSnackbar("Cuenta creada con éxito")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Cuenta")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onCancelarClick) {
            Text("Cancelar")
        }

        errorMensaje?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}


//la creacion de la cuenta esta funcionando con la validacion de la info sincroniso con el nav ya estamos avansando mas fluidos y sin errores 
