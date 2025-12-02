package com.milsabores.pasteleria.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import com.milsabores.pasteleria.R
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.TipoTorta
import com.milsabores.pasteleria.data.models.Tamano
import com.milsabores.pasteleria.ui.components.ProductoCard
import com.milsabores.pasteleria.viewmodel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        val productos = listOf(
            Producto(
                id = 1,
                nombre = "Promoción Bodas",
                descripcion = "Hermosa torta decorada especial para bodas",
                imagenResId = R.drawable.promocion_bodas,
                tipo = TipoTorta.CIRCULAR,
                preciosPorTamano = mapOf(
                    Tamano.PEQUENA to 15000,
                    Tamano.INTERMEDIA to 25000,
                    Tamano.GIGANTE to 35000
                )
            ),
            Producto(
                id = 2,
                nombre = "Torta de Chocolate",
                descripcion = "Torta suave y cremosa",
                imagenResId = R.drawable.torta_chocolate,
                tipo = TipoTorta.CIRCULAR,
                preciosPorTamano = mapOf(
                    Tamano.PEQUENA to 14000,
                    Tamano.INTERMEDIA to 24000,
                    Tamano.GIGANTE to 34000
                )
            ),
            Producto(
                id = 3,
                nombre = "Torta de Fresa",
                descripcion = "Fresas naturales y crema fresca",
                imagenResId = R.drawable.torta_fresa,
                tipo = TipoTorta.CIRCULAR,
                preciosPorTamano = mapOf(
                    Tamano.PEQUENA to 16000,
                    Tamano.INTERMEDIA to 26000,
                    Tamano.GIGANTE to 36000
                )
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(productos) { producto ->

                var cantidad by remember { mutableStateOf(1) }

                ProductoCard(
                    producto = producto,
                    cantidad = cantidad,
                    onCantidadChange = { cantidad = it },
                    onAgregar = { tamano ->

                        scope.launch {
                            try {
                                carritoViewModel.agregar(
                                    productoId = producto.id,
                                    cantidad = cantidad,
                                    tamano = tamano.name
                                )

                                // *** VALIDACIÓN / MENSAJE ***
                                snackbarHostState.showSnackbar(
                                    message = "✔ ${producto.nombre} agregado al carrito",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Short
                                )

                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar(
                                    message = "Error al agregar: ${e.message ?: "Intenta nuevamente"}",
                                    withDismissAction = true
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}




