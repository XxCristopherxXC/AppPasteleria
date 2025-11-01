package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.data.models.ItemCarrito

@Composable
fun CarritoItem(
    item: ItemCarrito,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen corregida usando imagenResId
            Image(
                painter = painterResource(id = item.producto.imagenResId),
                contentDescription = item.producto.nombre,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.producto.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Tamaño: ${item.tamano}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (item.mensaje.isNotEmpty()) {
                    Text(
                        text = "Mensaje: ${item.mensaje}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Subtotal: ${item.calcularSubtotal()} CLP",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDecrement) {
                        Text("-", style = MaterialTheme.typography.titleLarge)
                    }
                    Text(
                        text = "${item.cantidad}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = onIncrement) {
                        Text("+", style = MaterialTheme.typography.titleLarge)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(onClick = onRemove) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
