package com.prieto.tecsupfit.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import com.prieto.tecsupfit.ui.pantallas.PantallaPerfil
import com.prieto.tecsupfit.ui.pantallas.PantallaReservas
import com.prieto.tecsupfit.ui.pantallas.PantallaRutinas

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = bottomBarScreens.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    bottomBarScreens.forEach { screen ->
                        val selected = currentRoute == screen.route
                        val activeColor = Color(0xFF0F6D58)
                        val inactiveColor = Color(0xFF757575)

                        NavigationBarItem(
                            icon = {
                                Box(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .border(
                                            width = 2.dp,
                                            color = if (selected) activeColor else inactiveColor,
                                            shape = CircleShape
                                        )
                                )
                            },
                            label = {
                                Text(
                                    text = screen.title,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = activeColor,
                                unselectedTextColor = inactiveColor,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Inicio.route,
            modifier = Modifier.padding(innerPadding),
            // Se desactivan las animaciones de cambio de pantalla únicamente:
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
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
                        navController.navigate(Screen.Reservas.route) {
                            popUpTo(Screen.Inicio.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Reservas.route) {
                PantallaReservas()
            }
            composable(Screen.Rutinas.route) {
                PantallaRutinas()
            }
            composable(Screen.Perfil.route) {
                PantallaPerfil()
            }
        }
    }
}