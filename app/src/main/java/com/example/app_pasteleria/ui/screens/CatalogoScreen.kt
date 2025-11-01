package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Tamano
import com.milsabores.pasteleria.data.models.TipoTorta

@Composable
fun CatalogoScreen(
    productos: List<Producto>,
    onProductoClick: (Producto, Tamano) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var filtro by remember { mutableStateOf<TipoTorta?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título del catálogo
        Text(
            text = "Catálogo de Productos",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        // Filtro por tipo de torta
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { filtro = null }, modifier = Modifier.weight(1f)) {
                Text("Todos", fontSize = 12.sp)
            }
            Button(onClick = { filtro = TipoTorta.CUADRADA }, modifier = Modifier.weight(1f)) {
                Text("Cuadradas", fontSize = 12.sp)
            }
            Button(onClick = { filtro = TipoTorta.CIRCULAR }, modifier = Modifier.weight(1f)) {
                Text("Circulares", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val productosFiltrados = remember(filtro) {
            if (filtro != null) productos.filter { it.tipo == filtro } else productos
        }

        // Lista scrollable de productos
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productosFiltrados) { producto ->
                var tamanoSeleccionado by remember { mutableStateOf<Tamano?>(null) }

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(producto.imagenResId),
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                        Text(producto.descripcion, style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Tamano.values().forEach { tamano ->
                                Button(
                                    onClick = {
                                        tamanoSeleccionado = tamano
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Has seleccionado el tamaño: ${tamano.name}")
                                        }
                                    },
                                    modifier = Modifier.height(36.dp) // botones más pequeños
                                ) {
                                    Text(tamano.name, fontSize = 12.sp)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                tamanoSeleccionado?.let { onProductoClick(producto, it) }
                                coroutineScope.launch {
                                    if (tamanoSeleccionado != null) {
                                        snackbarHostState.showSnackbar(
                                            "Producto agregado al carrito: ${producto.nombre} (${tamanoSeleccionado!!.name})"
                                        )
                                    } else {
                                        snackbarHostState.showSnackbar(
                                            "Selecciona un tamaño antes de agregar al carrito"
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Agregar al Carrito")
                        }
                    }
                }
            }
        }

        // Snackbar host para mensajes
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SnackbarHost(hostState = snackbarHostState)
        }

        // Footer visible siempre al final
        FooterCatalogo()
    }
}

@Composable
fun FooterCatalogo() {
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

// esto fue largo pero funciono se creo el catalogo con drewale donde estan import la imgenes y con validacion de cada boton ademas 
// se aplica el filtro correctamente continuare con el mainactivity ya que me arroja mas errores con este cambio 








