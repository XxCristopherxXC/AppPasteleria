package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.milsabores.pasteleria.R

data class PromocionEjemplo(
    val titulo: String,
    val descripcion: String,
    val precioNormal: String?,
    val precioPromocion: String?,
    val codigo: String,
    val validez: String
)

@Composable
fun PromocionesScreen(
    onVolverInicioClick: () -> Unit
) {
    val promocionesEjemplo = listOf(
        PromocionEjemplo(
            titulo = "Torta de Chocolate Especial",
            descripcion = "Deliciosa torta de chocolate con descuento del 25% este mes",
            precioNormal = "$30.000",
            precioPromocion = "$22.500",
            codigo = "CHOCO25",
            validez = "31 de Enero 2025"
        ),
        PromocionEjemplo(
            titulo = "Combo Familiar",
            descripcion = "Lleva 2 tortas medianas por el precio de 1 grande",
            precioNormal = "$35.000",
            precioPromocion = null,
            codigo = "COMBO2X1",
            validez = "29 de Febrero 2025"
        ),
        PromocionEjemplo(
            titulo = "Descuento por Cumpleaños",
            descripcion = "15% de descuento en tu torta de cumpleaños presentando tu cédula",
            precioNormal = null,
            precioPromocion = null,
            codigo = "CUMPLE15",
            validez = "Válido todo el año"
        ),
        PromocionEjemplo(
            titulo = "Torta de Bodas",
            descripcion = "Hermosa torta de bodas con diseño personalizado",
            precioNormal = "$120.000",
            precioPromocion = "$100.000",
            codigo = "BODAS20",
            validez = "31 de Diciembre 2025"
        )
    )

    val promocionesImagenes = listOf(
        R.drawable.promocion_bodas,
        R.drawable.promocion_chocolate,
        R.drawable.promocion_familiar
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            item {
                Text(
                    text = "Promociones Especiales",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )

                // Botón para volver al inicio
                Button(
                    onClick = onVolverInicioClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text("Volver a Inicio")
                }

                // Imágenes de promoción
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(promocionesImagenes) { imagenResId ->
                        Card(
                            modifier = Modifier
                                .width(250.dp)
                                .height(180.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(imagenResId),
                                contentDescription = "Promoción",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // Información detallada de promociones
            items(promocionesEjemplo) { promo ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(promo.titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(promo.descripcion, style = MaterialTheme.typography.bodyMedium)
                    promo.precioNormal?.let {
                        Text("Precio normal: $it", style = MaterialTheme.typography.bodyMedium)
                    }
                    promo.precioPromocion?.let {
                        Text("Precio promoción: $it", style = MaterialTheme.typography.bodyMedium)
                    }
                    Text("Código: ${promo.codigo}", style = MaterialTheme.typography.bodyMedium)
                    Text("Válido hasta: ${promo.validez}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                // Cómo aprovechar las promociones
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Cómo aprovechar nuestras promociones", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        """
                        - Elige la torta que más te guste de nuestras ofertas
                        - Apunta el código de descuento correspondiente
                        - Visita nuestra tienda física o llama para hacer tu pedido
                        - Presenta el código al momento de la compra
                        - ¡Disfruta tu deliciosa torta con descuento!
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Términos y Condiciones", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        """
                        - Las promociones no son acumulables con otras ofertas
                        - Válido solo para compras realizadas en nuestra tienda física
                        - Sujeto a disponibilidad de stock
                        - Los códigos de descuento deben presentarse al momento de la compra
                        - Promociones válidas solo durante las fechas indicadas
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Footer exclusivo
        FooterPromociones()
    }
}

@Composable
fun FooterPromociones() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.Start
    ) {
        Text("Dirección: Av. Américo Vespucio 1501, Cerrillos, Región Metropolitana")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Teléfono: +52 33 1427 2887")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Redes Sociales: Facebook - Mil Sabores Cake Shop")
    }
}


