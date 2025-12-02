package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Tamano

@Composable
fun ProductoCard(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    onAgregar: (Tamano) -> Unit
) {
    var tamanoSeleccionado by remember { mutableStateOf(Tamano.INTERMEDIA) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Tamano.values().forEach { t ->
                    FilterChip(
                        selected = tamanoSeleccionado == t,
                        onClick = { tamanoSeleccionado = t },
                        label = { Text(t.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { if (cantidad > 1) onCantidadChange(cantidad - 1) }
                ) { Text("-") }

                Text(
                    text = cantidad.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedButton(
                    onClick = { onCantidadChange(cantidad + 1) }
                ) { Text("+") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onAgregar(tamanoSeleccionado) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}



