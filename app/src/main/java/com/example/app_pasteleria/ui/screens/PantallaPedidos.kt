package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.milsabores.pasteleria.R

@Composable
fun PantallaPedidos() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Tus Pedidos",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Aquí podrás ver y gestionar tus pedidos.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }

        // Pedidos de ejemplo
        items(getPedidosEjemplo()) { pedido ->
            PedidoCard(
                numero = pedido.numero,
                producto = pedido.producto,
                cantidad = pedido.cantidad,
                estado = pedido.estado,
                imagenResId = pedido.imagenResId
            )
        }

        // Footer
        item { FooterPedidos() }
    }
}

data class Pedido(
    val numero: String,
    val producto: String,
    val cantidad: Int,
    val estado: String,
    val imagenResId: Int
)

fun getPedidosEjemplo(): List<Pedido> = listOf(
    Pedido("#001", "Pastel de Chocolate", 1, "En preparación", R.drawable.torta_chocolate),
    Pedido("#002", "Cupcakes de Vainilla", 6, "En camino", R.drawable.torta_vainilla),
    Pedido("#003", "Tarta de Fresas", 1, "Entregado", R.drawable.torta_fresa)
)

@Composable
fun PedidoCard(numero: String, producto: String, cantidad: Int, estado: String, imagenResId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Pedido $numero", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Imagen de la torta
            Image(
                painter = painterResource(id = imagenResId),
                contentDescription = producto,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text("Producto: $producto", style = MaterialTheme.typography.bodyMedium)
                Text("Cantidad: $cantidad", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Estado de entrega: $estado",
                    color = when (estado) {
                        "En preparación" -> Color(0xFF1976D2)
                        "En camino" -> Color(0xFFFFA000)
                        "Entregado" -> Color(0xFF388E3C)
                        else -> Color.Black
                    }
                )
            }
        }
    }
}

@Composable
fun FooterPedidos() {
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
//esta creada la ocuparemos ?? ya se vera por ahora dejarlo asi no eliminar 





