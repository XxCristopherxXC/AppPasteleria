package com.milsabores.pasteleria.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.milsabores.pasteleria.viewmodel.CarritoViewModel
import com.example.app_pasteleria.network.CarritoItemResponse
import kotlinx.coroutines.launch

@Composable
fun CarritoScreen(
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val items: List<CarritoItemResponse> by carritoViewModel.carrito.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        carritoViewModel.cargarCarrito()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        if (items.isEmpty()) {
            Text("El carrito está vacío", style = MaterialTheme.typography.headlineMedium)
        } else {

            // Botón para vaciar todo el carrito
            Button(
                onClick = {
                    scope.launch {
                        try {
                            carritoViewModel.vaciar()
                            Toast.makeText(context, "Carrito vaciado", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error al vaciar carrito: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Vaciar Carrito")
            }

            items.forEach { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Text("Item ID: ${item.id}", style = MaterialTheme.typography.titleMedium)
                        Text("Producto ID: ${item.idProducto}")
                        Text("Tamaño: ${item.tamano}")
                        Text("Cantidad: ${item.cantidad}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                Log.d("CarritoScreen", "Botón eliminar presionado. Item ID: ${item.id}")
                                scope.launch {
                                    try {
                                        Log.d("CarritoScreen", "Llamando a eliminar para item: ${item.id}")
                                        carritoViewModel.eliminar(item.id)
                                        Log.d("CarritoScreen", "Eliminación completada")
                                        Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show()
                                    } catch (e: Exception) {
                                        Log.e("CarritoScreen", "Error al eliminar: ${e.message}", e)
                                        Toast.makeText(context, "Error al eliminar: ${e.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        ) {
                            Text("Eliminar del Carrito")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Finalizar Compra
            Button(
                onClick = {
                    if (items.isEmpty()) {
                        Toast.makeText(context, "El carrito está vacío", Toast.LENGTH_SHORT).show()
                    } else {
                        scope.launch {
                            try {
                                // Calcular total de items antes de vaciar
                                val totalItems = items.sumOf { it.cantidad }

                                Log.d("CarritoScreen", "Finalizando compra con ${items.size} items")

                                // Vaciar el carrito después de finalizar la compra
                                carritoViewModel.vaciar()

                                Toast.makeText(
                                    context,
                                    "¡Compra finalizada! Total: $totalItems producto(s)",
                                    Toast.LENGTH_LONG
                                ).show()

                                Log.d("CarritoScreen", "Compra finalizada y carrito vaciado exitosamente")

                            } catch (e: Exception) {
                                Log.e("CarritoScreen", "Error al finalizar compra: ${e.message}", e)
                                Toast.makeText(
                                    context,
                                    "Error al finalizar compra: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar Compra")
            }
        }
    }
}
