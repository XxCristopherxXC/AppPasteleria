package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.milsabores.pasteleria.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.milsabores.pasteleria.viewmodel.PromocionesViewModel

data class Promocion(
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
    // -----------------------
    // PROMOCIONES LOCALES
    // -----------------------
    val promociones = listOf(
        Promocion(
            titulo = "Torta de Chocolate Especial",
            descripcion = "25% de descuento este mes",
            precioNormal = "$30.000",
            precioPromocion = "$22.500",
            codigo = "CHOCO25",
            validez = "31 de Enero 2025"
        ),
        Promocion(
            titulo = "Combo Familiar 2x1",
            descripcion = "2 tortas medianas por el precio de 1 grande",
            precioNormal = "$35.000",
            precioPromocion = null,
            codigo = "COMBO2X1",
            validez = "29 de Febrero 2025"
        )
    )

    // -----------------------
    // IMÁGENES LOCALES
    // -----------------------
    val imagenes = listOf(
        R.drawable.promocion_chocolate,
        R.drawable.promocion_familiar,
        R.drawable.promocion_bodas
    )

    // -----------------------
    // VIEWMODEL API EXTERNA
    // -----------------------
    val apiViewModel: PromocionesViewModel = viewModel()
    val promosApi by apiViewModel.promosApi.collectAsState()
    val cargandoApi by apiViewModel.cargando.collectAsState()

    // -----------------------
    // UI GENERAL
    // -----------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // -----------------------
            // TITULO + BOTÓN INICIO + CARRUSEL
            // -----------------------
            item {
                Text(
                    text = "Promociones Especiales",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onVolverInicioClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Volver al Inicio")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // -----------------------
                // CARRUSEL DE IMÁGENES
                // -----------------------
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(imagenes) { img ->
                        Card(
                            modifier = Modifier
                                .width(260.dp)
                                .height(170.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(img),
                                contentDescription = "Promoción",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // -----------------------
            // PROMOCIONES LOCALES (TU DISEÑO ORIGINAL)
            // -----------------------
            items(promociones) { promo ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = promo.titulo,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = promo.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        promo.precioNormal?.let {
                            Text("Precio normal: $it", style = MaterialTheme.typography.bodyMedium)
                        }

                        promo.precioPromocion?.let {
                            Text(
                                "Precio promoción: $it",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text("Código: ${promo.codigo}", style = MaterialTheme.typography.bodyMedium)
                        Text("Válido hasta: ${promo.validez}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            // -----------------------
            // API EXTERNA (NUEVA SECCIÓN)
            // -----------------------
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Promociones API Externa",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (cargandoApi) {
                    CircularProgressIndicator()
                } else if (promosApi.isEmpty()) {
                    Text("No hay promociones disponibles en este momento.")
                } else {
                    promosApi.forEach { promo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                Image(
                                    painter = rememberAsyncImagePainter(promo.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(promo.title, fontWeight = FontWeight.Bold)
                                Text(promo.description)
                                Text(
                                    "Precio: $${promo.price}",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            // -----------------------
            // INFORMACIÓN ADICIONAL
            // -----------------------
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Cómo aprovechar nuestras promociones",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "- Elige la promoción\n" +
                                "- Toma el código\n" +
                                "- Preséntalo al pagar\n",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Términos y Condiciones",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        """
                        - No acumulables
                        - Sujeto a stock
                        - Debe presentarse el código
                        - Válidas solo en las fechas indicadas
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

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
        horizontalAlignment = Alignment.Start
    ) {
        Text("Dirección: Av. Américo Vespucio 1501, Cerrillos, Región Metropolitana")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Teléfono: +52 33 1427 2887")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Facebook: Mil Sabores Cake Shop")
    }
}
