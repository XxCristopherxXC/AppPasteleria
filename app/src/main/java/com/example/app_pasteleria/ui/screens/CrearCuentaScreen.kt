package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Título
            Text(
                text = "Crear Cuenta",
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onCuentaCreada(nombre, apellido, correo, contrasena)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Cuenta creada con éxito")
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Crear Cuenta")
                }

                OutlinedButton(
                    onClick = onCancelarClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }
            }

            // Snackbar
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }

        // Footer al final
        FooterCrearCuenta()
    }
}

@Composable
fun FooterCrearCuenta() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Dirección: Av. Américo Vespucio 1501, Cerrillos, Región Metropolitana")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Teléfono: +52 33 1427 2887")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Redes Sociales: Facebook - Mil Sabores Cake Shop")
    }
}

//la creacion de la cuenta esta funcionando con la validacion de la info sincroniso con el nav ya estamos avansando mas fluidos y sin errores 
