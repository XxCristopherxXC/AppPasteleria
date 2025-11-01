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
fun PerfilScreen(
    onCrearCuentaClick: () -> Unit,
    onCerrarSesionClick: () -> Unit
) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var preferencias by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Gestión de Perfil de Usuario",
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it },
                label = { Text("Nombre y Apellido") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección de envío") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = preferencias,
                onValueChange = { preferencias = it },
                label = { Text("Preferencias de compra") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Cambios guardados con éxito")
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar Cambios")
                }

                Button(
                    onClick = onCerrarSesionClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cerrar Sesión")
                }
            }

            // Snackbar en la parte inferior del contenido
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }

        // Footer siempre al final
        FooterPerfil()
    }
}

@Composable
fun FooterPerfil() {
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







