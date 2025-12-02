package com.milsabores.pasteleria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.milsabores.pasteleria.data.models.Promocion

@Composable
fun PromocionCard(promocion: Promocion) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = promocion.imagenResId),
                contentDescription = promocion.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(promocion.titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Oferta: \$${promocion.precioOferta}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
