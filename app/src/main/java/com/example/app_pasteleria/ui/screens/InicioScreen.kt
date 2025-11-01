package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.milsabores.pasteleria.data.repository.ProductosRepository
import com.milsabores.pasteleria.ui.components.PromocionCard

@Composable
fun InicioScreen(
    onVerCatalogoClick: () -> Unit,
    onHacerPedidoClick: () -> Unit,
    onCrearCuentaClick: () -> Unit,
    onPerfilClick: () -> Unit,
    onPromocionesClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column {
                // Encabezado
                Text(
                    text = "Pastelería Mil Sabores",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )

                Text(
                    text = "Los sabores más dulces de la vida",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                val botonColor = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

                // Botones principales (todos mismo color)
                Button(
                    onClick = onVerCatalogoClick,
                    colors = botonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Ver Catálogo de Productos", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onHacerPedidoClick,
                    colors = botonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Ir al Carrito", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onCrearCuentaClick,
                    colors = botonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Crear Cuenta", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onPerfilClick,
                    colors = botonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Perfil", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onPromocionesClick,
                    colors = botonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Ver Promociones", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección de promociones
                Text(
                    text = "Promociones Especiales",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ProductosRepository.getPromociones()) { promocion ->
                        PromocionCard(promocion = promocion)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Texto de bienvenida, misión y visión debajo de las promociones
                Text(
                    text = "Bienvenidos a nuestra pastelería",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "En Pastelería Mil Sabores creamos los pasteles más deliciosos para hacer de tus momentos especiales algo inolvidable. " +
                            "Cada torta está hecha con amor y los ingredientes más frescos.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Misión",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Ofrecer una experiencia dulce y memorable a nuestros clientes, " +
                            "proporcionando tortas y productos de repostería de alta calidad " +
                            "para todas las ocasiones, mientras celebramos nuestras raíces históricas " +
                            "y fomentamos la creatividad en la repostería.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Visión",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Convertirnos en la tienda online líder de productos de repostería en Chile, " +
                            "conocida por nuestra innovación, calidad y el impacto positivo en la comunidad, " +
                            "especialmente en la formación de nuevos talentos en gastronomía.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Footer
        item { FooterInicio() }
    }
}

@Composable
fun FooterInicio() {
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
        Text("Redes Sociales: Facebook - Mil Sabores Cake Shop")
    }
}





