package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Tamano

@Composable
fun ProductoCard(
    producto: Producto,
    onAgregarAlCarrito: (Producto, Tamano) -> Unit
) {
    var tamanoSeleccionado by remember { mutableStateOf(Tamano.INTERMEDIA) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(producto.imagenResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Tamano.values().forEach { t ->
                    Button(
                        onClick = { tamanoSeleccionado = t },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(t.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onAgregarAlCarrito(producto, tamanoSeleccionado) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al Carrito")
            }
        }
    }
}



