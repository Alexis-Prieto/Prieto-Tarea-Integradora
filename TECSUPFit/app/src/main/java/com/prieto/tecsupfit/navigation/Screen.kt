package com.prieto.tecsupfit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    object Inicio : Screen("inicio", "Inicio", Icons.Default.Home)
    object Reservas : Screen("reservas", "Reservas", Icons.Default.DateRange)
    object Rutinas : Screen("rutinas", "Rutinas", Icons.Default.FitnessCenter)
    object Perfil : Screen("perfil", "Perfil", Icons.Default.Person)

    object Detalle : Screen("detalle/{classId}") {
        fun createRoute(classId: Int) = "detalle/$classId"
    }
    object Confirmacion : Screen("confirmacion/{classId}") {
        fun createRoute(classId: Int) = "confirmacion/$classId"
    }
}

val bottomBarScreens = listOf(
    Screen.Inicio,
    Screen.Reservas,
    Screen.Rutinas,
    Screen.Perfil
)