package com.prieto.tecsupfit.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
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
                Column {
                    HorizontalDivider(color = Color(0xFFE5E5E5), thickness = 1.dp)

                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 0.dp
                    ) {
                        bottomBarScreens.forEach { screen ->
                            val selected = currentRoute == screen.route
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.RadioButtonUnchecked,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = screen.title,
                                        fontSize = 12.sp,
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                selected = selected,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color(0xFF0F6D58),
                                    selectedTextColor = Color(0xFF0F6D58),
                                    unselectedIconColor = Color(0xFF757575),
                                    unselectedTextColor = Color(0xFF757575),
                                    indicatorColor = Color.Transparent
                                ),
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
                PantallaReservas()
            }
            composable(Screen.Rutinas.route) {
                PantallaTemporal(title = "Rutinas de Entrenamiento")
            }
            composable(Screen.Perfil.route) {
                PantallaPerfil()
            }
        }
    }
}