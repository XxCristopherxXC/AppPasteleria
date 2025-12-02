package com.milsabores.pasteleria.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.milsabores.pasteleria.data.persistence.PreferenciasLocal
import com.milsabores.pasteleria.utils.LocationHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun PerfilScreen(
    onEliminarCuenta: () -> Unit,
    onVolverInicio: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("Ubicación no disponible") }

    // Cargar DataStore
    LaunchedEffect(Unit) {
        PreferenciasLocal.obtenerNombre(context).collectLatest { nombre = it ?: "" }
    }
    LaunchedEffect(Unit) {
        PreferenciasLocal.obtenerApellido(context).collectLatest { apellido = it ?: "" }
    }
    LaunchedEffect(Unit) {
        PreferenciasLocal.obtenerCorreo(context).collectLatest { correo = it ?: "" }
    }

    // GPS
    val locationHelper = remember { LocationHelper(context) }

    val permisoLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val loc = locationHelper.getUserLocation()
            if (loc != null) ubicacion = "Lat: ${loc.latitude}, Lon: ${loc.longitude}"
        }
    }

    LaunchedEffect(Unit) {
        val permiso = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permiso == PackageManager.PERMISSION_GRANTED) {
            val loc = locationHelper.getUserLocation()
            if (loc != null) ubicacion = "Lat: ${loc.latitude}, Lon: ${loc.longitude}"
        } else {
            permisoLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Perfil del Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre: $nombre")
                Spacer(Modifier.height(8.dp))
                Text("Apellido: $apellido")
                Spacer(Modifier.height(8.dp))
                Text("Correo: $correo")
                Spacer(Modifier.height(8.dp))
                Text("Ubicación: $ubicacion")
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onVolverInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Inicio")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                scope.launch {
                    PreferenciasLocal.limpiarDatos(context)
                    onEliminarCuenta()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Eliminar Cuenta")
        }
    }
}



