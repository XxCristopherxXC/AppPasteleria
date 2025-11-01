package com.milsabores.pasteleria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.milsabores.pasteleria.viewmodel.CarritoViewModel

@Composable
fun CarritoScreen(
    carritoViewModel: CarritoViewModel? = null,
    onFinalizarPedidoClick: () -> Unit = {},
    onIrAPedidosClick: () -> Unit = {} // Botón existente
) {
    // Estado para mostrar validación
    var mostrarMensaje by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Tu Carrito",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Revisa los productos antes de finalizar tu pedido.",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(getCarritoEjemplo()) { item ->
                    CarritoItemCard(item.nombre, item.cantidad, item.precio)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: $35.00",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Finalizar Pedido con validación
            Button(
                onClick = {
                    mostrarMensaje = true
                    onFinalizarPedidoClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Finalizar Pedido")
            }

            // Mensaje de validación (Snackbar)
            if (mostrarMensaje) {
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000)
                    mostrarMensaje = false
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "✅ Pedido finalizado con éxito",
                    color = Color(0xFF388E3C),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón Ir a Mis Pedidos
            Button(
                onClick = onIrAPedidosClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Ir a Mis Pedidos")
            }
        }

        FooterCarrito()
    }
}

data class CarritoItem(val nombre: String, val cantidad: Int, val precio: Double)

fun getCarritoEjemplo(): List<CarritoItem> {
    return listOf(
        CarritoItem("Pastel de Chocolate", 1, 15.0),
        CarritoItem("Cupcakes de Vainilla", 6, 12.0),
        CarritoItem("Tarta de Fresas", 1, 8.0)
    )
}

@Composable
fun CarritoItemCard(nombre: String, cantidad: Int, precio: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(nombre, style = MaterialTheme.typography.titleMedium)
                Text("Cantidad: $cantidad", style = MaterialTheme.typography.bodyMedium)
            }
            Text("$${precio * cantidad}", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun FooterCarrito() {
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




