package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.data.models.Producto
import com.milsabores.pasteleria.data.models.Tamano

@Composable
fun ProductoCardCatalogo(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    onAgregar: (Tamano) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Cantidad: ")
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { if (cantidad > 1) onCantidadChange(cantidad - 1) }) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("$cantidad")
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onCantidadChange(cantidad + 1) }) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                producto.preciosPorTamano.keys.forEach { tamano ->
                    Button(onClick = { onAgregar(tamano) }) {
                        Text(tamano.name)
                    }
                }
            }
        }
    }
}
