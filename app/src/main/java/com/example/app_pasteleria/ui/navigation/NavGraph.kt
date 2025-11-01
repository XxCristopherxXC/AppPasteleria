package com.milsabores.pasteleria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.milsabores.pasteleria.data.repository.ProductosRepository
import com.milsabores.pasteleria.ui.screens.*
import com.milsabores.pasteleria.viewmodel.CarritoViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val carritoViewModel: CarritoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "inicio",
        modifier = modifier
    ) {
        composable("inicio") {
            InicioScreen(
                onVerCatalogoClick = { navController.navigate("catalogo") },
                onHacerPedidoClick = { navController.navigate("carrito") },
                onCrearCuentaClick = { navController.navigate("crear_cuenta") },
                onPerfilClick = { navController.navigate("perfil") },
                onPromocionesClick = { navController.navigate("promociones") }
            )
        }

        composable("catalogo") {
            CatalogoScreen(
                productos = ProductosRepository.getProductos(),
                onProductoClick = { producto, tamano ->
                    carritoViewModel.agregarProducto(producto, tamano)
                }
            )
        }

        composable("carrito") {
            CarritoScreen(
                carritoViewModel = carritoViewModel,
                onFinalizarPedidoClick = { carritoViewModel.vaciarCarrito() },
                onIrAPedidosClick = { navController.navigate("pantalla_pedidos") } // nuevo
            )
        }

        composable("pantalla_pedidos") {
            PantallaPedidos() // tu pantalla de pedidos
        }

        composable("perfil") {
            PerfilScreen(
                onCrearCuentaClick = { navController.navigate("crear_cuenta") },
                onCerrarSesionClick = { /* cerrar sesión */ }
            )
        }

        composable("crear_cuenta") {
            CrearCuentaScreen(
                onCuentaCreada = { nombre, apellido, correo, contrasena ->
                    println("Cuenta creada: $nombre $apellido $correo $contrasena")
                    navController.popBackStack()
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        composable("promociones") {
            PromocionesScreen(
                onVolverInicioClick = { navController.navigate("inicio") }
            )
        }
    }
}

// nav funcionando correctamente con inicio ya entendiendo que funciona igual que html se iso mas rapido el trabajo 



