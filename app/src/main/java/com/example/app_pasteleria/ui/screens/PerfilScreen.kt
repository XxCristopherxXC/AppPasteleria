package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.viewmodel.UsuariosViewModel

@Composable
fun PerfilScreen(
    usuarioViewModel: UsuariosViewModel,
    onCerrarSesion: () -> Unit,
    onVolverInicio: () -> Unit = {}  // Parámetro opcional para volver al inicio
) {
    val usuario by usuarioViewModel.usuario.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (usuario != null) {
            Text(
                text = "Perfil del Usuario",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nombre: ${usuario!!.nombre}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Apellido: ${usuario!!.apellido}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Correo: ${usuario!!.correo}", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para volver al inicio
            Button(
                onClick = onVolverInicio,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Inicio")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón de cerrar sesión
            Button(
                onClick = {
                    usuarioViewModel.cerrarSesion()
                    onCerrarSesion()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Cerrar Sesión")
            }
        } else {
            Text(
                text = "No hay sesión activa",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Por favor, crea una cuenta primero")

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para volver al inicio cuando no hay sesión
            Button(
                onClick = onVolverInicio,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Inicio")
            }
        }
    }
}


