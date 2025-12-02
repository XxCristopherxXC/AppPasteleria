package com.milsabores.pasteleria.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
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

        // PANTALLA DE INICIO
        composable("inicio") {
            InicioScreen(
                onVerCatalogoClick = { navController.navigate("catalogo") },
                onHacerPedidoClick = { navController.navigate("carrito") },
                onCrearCuentaClick = { navController.navigate("crearCuenta") },
                onPerfilClick = { navController.navigate("perfil") },
                onPromocionesClick = { navController.navigate("promociones") }
            )
        }

        // CATALOGO
        composable("catalogo") {
            CatalogoScreen()
        }

        // CARRITO REAL
        composable("carrito") {
            CarritoScreen()
        }

        // PERFIL
        composable("perfil") {
            PerfilScreen(
                usuarioViewModel = viewModel(),
                onCerrarSesion = { navController.navigate("inicio") },
                onVolverInicio = { navController.navigate("inicio") }
            )
        }

        // PROMOCIONES
        composable("promociones") {
            PromocionesScreen(
                onVolverInicioClick = { navController.navigate("inicio") }
            )
        }

        // CREAR CUENTA
        composable("crearCuenta") {
            CrearCuentaScreen(
                onCuentaCreada = { nombre, apellido, correo, contrasena ->
                    // Aquí podrías guardar el usuario con el ViewModel
                    // usuarioViewModel.crearCuenta(nombre, apellido, correo, contrasena)
                    navController.navigate("inicio")
                },
                onCancelarClick = { navController.navigate("inicio") }
            )
        }
    }
}
