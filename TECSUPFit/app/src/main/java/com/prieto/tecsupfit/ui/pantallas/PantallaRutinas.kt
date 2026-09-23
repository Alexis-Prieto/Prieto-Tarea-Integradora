package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RutinaItem(
    val id: Int,
    val nombre: String,
    val duracion: String,
    val nivel: String,
    val ejercicios: Int,
    val categoria: String
)

val sampleRutinas = listOf(
    RutinaItem(1, "Rutina Torso - Fuerza", "45 min", "Intermedio", 6, "Fuerza"),
    RutinaItem(2, "Fullbody Quema Grasa", "30 min", "Principiante", 5, "Cardio"),
    RutinaItem(3, "Hipertrofia Espalda y Biceps", "50 min", "Avanzado", 7, "Fuerza"),
    RutinaItem(4, "Core & Abdominales", "20 min", "Todos", 4, "Core"),
    RutinaItem(5, "Pierna & Glúteos", "55 min", "Intermedio", 6, "Fuerza"),
    RutinaItem(6, "HIIT Express - Quema Calórica", "25 min", "Avanzado", 8, "Cardio")
)

@Composable
fun PantallaRutinas() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = "Rutinas de entrenamiento",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        items(sampleRutinas) { rutina ->
            TarjetaRutina(rutina = rutina)
        }
    }
}

@Composable
fun TarjetaRutina(rutina: RutinaItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0F2EE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = Color(0xFF0F6D58),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = rutina.nombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${rutina.duracion} · ${rutina.ejercicios} ejercicios · ${rutina.nivel}",
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }
        }
    }
}