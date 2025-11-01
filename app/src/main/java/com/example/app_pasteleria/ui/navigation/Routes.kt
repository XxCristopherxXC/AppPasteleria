package com.milsabores.pasteleria.navigation

sealed class Routes(val route: String) {
    object Inicio : Routes("inicio")
    object Catalogo : Routes("catalogo")
    object Carrito : Routes("carrito")
    object Perfil : Routes("perfil")
}
// actualisar todas las rutas 
