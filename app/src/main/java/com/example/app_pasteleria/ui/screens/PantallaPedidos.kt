package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.data.models.ItemCarrito

@Composable
fun PantallaPedidos(
    pedidos: List<ItemCarrito> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mis Pedidos", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (pedidos.isEmpty()) {
            Text("No tienes pedidos realizados.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(pedidos) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(item.producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Cantidad: ${item.cantidad}")
                            Text("Tamaño: ${item.tamano}")
                            Text("Subtotal: ${item.calcularSubtotal()} CLP")
                        }
                    }
                }
            }
        }
    }
}

//esta creada la ocuparemos ?? ya se vera por ahora dejarlo asi no eliminar 





