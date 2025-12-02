package com.milsabores.pasteleria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.milsabores.pasteleria.ui.screens.InicioScreen
import com.milsabores.pasteleria.ui.screens.CatalogoScreen
import com.milsabores.pasteleria.ui.screens.CarritoScreen
import com.milsabores.pasteleria.ui.screens.PromocionesScreen
import com.milsabores.pasteleria.ui.screens.PerfilScreen
import com.milsabores.pasteleria.ui.screens.CrearCuentaScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {
            InicioScreen(
                onVerCatalogoClick = { navController.navigate("catalogo") },
                onHacerPedidoClick = { navController.navigate("carrito") },
                onCrearCuentaClick = { navController.navigate("crearCuenta") },
                onPerfilClick = { navController.navigate("perfil") },
                onPromocionesClick = { navController.navigate("promociones") }
            )
        }

        composable("catalogo") {
            CatalogoScreen()
        }

        composable("carrito") {
            CarritoScreen()
        }

        composable("promociones") {
            PromocionesScreen(
                onVolverInicioClick = { navController.navigate("inicio") }
            )
        }

        composable("perfil") {
            PerfilScreen(
                onEliminarCuenta = { navController.navigate("inicio") },
                onVolverInicio = { navController.navigate("inicio") }
            )
        }

        composable("crearCuenta") {
            CrearCuentaScreen(
                onCuentaCreada = { _, _, _, _ -> /* No volver al inicio */ },
                onCancelarClick = { navController.navigate("inicio") }
            )
        }
    }
}
