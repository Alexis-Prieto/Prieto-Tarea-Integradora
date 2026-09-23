package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Ejercicio(
    val nombre: String,
    val series: String,
    val descanso: String
)

data class RutinaDia(
    val dia: String,
    val titulo: String,
    val ejercicios: List<Ejercicio>
)

@Composable
fun PantallaRutinas() {
    val rutinas = listOf(
        RutinaDia(
            dia = "Lunes",
            titulo = "Torso y Fuerza Upper",
            ejercicios = listOf(
                Ejercicio("Press de Banca con Barra", "4 series x 10 reps", "90 seg"),
                Ejercicio("Remo con Mancuerna", "4 series x 12 reps", "60 seg"),
                Ejercicio("Press Militar de Hombros", "3 series x 10 reps", "60 seg")
            )
        ),
        RutinaDia(
            dia = "Miércoles",
            titulo = "Pierna y Core Lower",
            ejercicios = listOf(
                Ejercicio("Sentadilla Profunda con Barra", "4 series x 8 reps", "120 seg"),
                Ejercicio("Peso Muerto Rumano", "3 series x 10 reps", "90 seg"),
                Ejercicio("Prensa 45°", "3 series x 12 reps", "60 seg")
            )
        ),
        RutinaDia(
            dia = "Viernes",
            titulo = "Hipertrofia Fullbody",
            ejercicios = listOf(
                Ejercicio("Dominadas / Jalón al Pecho", "4 series x 10 reps", "60 seg"),
                Ejercicio("Zancadas con Mancuernas", "3 series x 12 reps", "60 seg"),
                Ejercicio("Fondos en Paralelas", "3 series x al fallo", "60 seg")
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Rutinas de Entrenamiento",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1E),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Tarjeta de resumen de progreso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0F2EE))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Plan Semanal Personalizado",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F6D58),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "3 días asignados esta semana",
                        color = Color(0xFF424242),
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = "100%",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F6D58),
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(rutinas) { rutina ->
                TarjetaRutina(rutina = rutina)
            }
        }
    }
}

@Composable
fun TarjetaRutina(rutina: RutinaDia) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = rutina.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0F6D58))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = rutina.dia,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            rutina.ejercicios.forEachIndexed { index, ej ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ej.nombre,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF333333)
                        )
                        Text(
                            text = ej.series,
                            fontSize = 11.sp,
                            color = Color(0xFF757575)
                        )
                    }
                    Text(
                        text = ej.descanso,
                        fontSize = 11.sp,
                        color = Color(0xFF0F6D58),
                        fontWeight = FontWeight.Bold
                    )
                }
                if (index < rutina.ejercicios.size - 1) {
                    Divider(color = Color(0xFFE0E0E0), thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}