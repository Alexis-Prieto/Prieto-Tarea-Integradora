package com.prieto.tecsupfit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prieto.tecsupfit.ui.pantallas.PantallaConfirmacion
import com.prieto.tecsupfit.ui.pantallas.PantallaDetalle
import com.prieto.tecsupfit.ui.pantallas.PantallaInicio
import com.prieto.tecsupfit.ui.pantallas.PantallaTemporal

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = bottomBarScreens.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarScreens.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                screen.icon?.let {
                                    Icon(imageVector = it, contentDescription = screen.title)
                                }
                            },
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Inicio.route) {
                PantallaInicio(onClaseClick = { classId ->
                    navController.navigate(Screen.Detalle.createRoute(classId))
                })
            }
            composable(
                route = Screen.Detalle.route,
                arguments = listOf(navArgument("classId") { type = NavType.IntType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getInt("classId") ?: 0
                PantallaDetalle(
                    classId = classId,
                    onVolver = { navController.popBackStack() },
                    onReservar = { id ->
                        navController.navigate(Screen.Confirmacion.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.Confirmacion.route,
                arguments = listOf(navArgument("classId") { type = NavType.IntType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getInt("classId") ?: 0
                PantallaConfirmacion(
                    classId = classId,
                    onIrAInicio = {
                        navController.navigate(Screen.Inicio.route) {
                            popUpTo(Screen.Inicio.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Reservas.route) {
                PantallaTemporal(title = "Mis Reservas (RF03)")
            }
            composable(Screen.Rutinas.route) {
                PantallaTemporal(title = "Rutinas de Entrenamiento")
            }
            composable(Screen.Perfil.route) {
                PantallaTemporal(title = "Mi Perfil (RF04)")
            }
        }
    }
}