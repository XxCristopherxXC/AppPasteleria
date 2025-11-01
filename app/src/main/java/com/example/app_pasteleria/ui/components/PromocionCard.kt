package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.data.models.Promocion

@Composable
fun PromocionCard(promocion: Promocion) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Imagen usando recurso local
            Image(
                painter = painterResource(id = promocion.imagenResId),
                contentDescription = promocion.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = promocion.titulo,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = promocion.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "${promocion.precioOriginal} CLP",
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${promocion.precioOferta} CLP",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Código: ${promocion.codigo}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Válido hasta: ${promocion.validezHasta}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
