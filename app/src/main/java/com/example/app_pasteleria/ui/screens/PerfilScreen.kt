package com.milsabores.pasteleria.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import com.milsabores.pasteleria.data.persistence.PreferenciasLocal
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PerfilScreen(
    onCrearCuentaClick: () -> Unit,
    onCerrarSesionClick: () -> Unit
) {
    val context = LocalContext.current
    var nombreCompleto by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("Ubicación no disponible") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Obtener nombre desde DataStore
    LaunchedEffect(Unit) {
        PreferenciasLocal.obtenerNombre(context).collectLatest { storedName ->
            storedName?.let { nombreCompleto = it }
        }
    }

    // Permisos GPS
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) obtenerUbicacion(context) { ubicacion = it }
        else ubicacion = "Permiso de ubicación denegado"
    }

    LaunchedEffect(Unit) {
        val permiso = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permiso == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion(context) { ubicacion = it }
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

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

            Text("Ubicación actual: $ubicacion", style = MaterialTheme.typography.bodyMedium)

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onCrearCuentaClick,
                    modifier = Modifier.weight(1f)
                ) { Text("Crear Cuenta") }

                Button(
                    onClick = onCerrarSesionClick,
                    modifier = Modifier.weight(1f)
                ) { Text("Cerrar Sesión") }
            }

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }

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

private fun obtenerUbicacion(context: android.content.Context, onUbicacionObtenida: (String) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                onUbicacionObtenida("Lat: ${it.latitude}, Lon: ${it.longitude}")
            } ?: onUbicacionObtenida("Ubicación no disponible")
        }
    } catch (e: SecurityException) {
        onUbicacionObtenida("Permiso denegado")
    }
}

// la validacion al ingresar in info esta funcionando bien sigo con la siguiente de carrittos 






