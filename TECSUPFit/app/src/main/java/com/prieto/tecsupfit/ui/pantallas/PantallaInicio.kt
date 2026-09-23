package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prieto.tecsupfit.data.GymClass
import com.prieto.tecsupfit.data.GymRepository

@Composable
fun PantallaInicio(
    onClaseClick: (Int) -> Unit = {}
) {
    var filtroSeleccionado by remember { mutableStateOf("Hoy") }

    val clasesFiltradas = GymRepository.sampleClasses.filter { clase ->
        clase.filterTag == filtroSeleccionado
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F6D58))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                Text(
                    text = "TECSUP Fit",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Hola, Alexis",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(GymRepository.dateFilters) { filtro ->
                    val esSeleccionado = filtroSeleccionado == filtro
                    Box(
                        modifier = Modifier
                            .clip(if (esSeleccionado) CircleShape else RoundedCornerShape(16.dp))
                            .background(
                                if (esSeleccionado) Color(0xFF0F6D58) else Color(0xFFF0F0F0)
                            )
                            .clickable { filtroSeleccionado = filtro }
                            .padding(
                                horizontal = if (esSeleccionado) 20.dp else 16.dp,
                                vertical = 10.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filtro,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (esSeleccionado) Color.White else Color(0xFF444444)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Clases disponibles",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E)
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(clasesFiltradas) { clase ->
                    TarjetaClaseOficial(
                        clase = clase,
                        onClick = { onClaseClick(clase.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaClaseOficial(
    clase: GymClass,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFE0F2EE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = Color(0xFF0F6D58),
                    modifier = Modifier
                        .size(26.dp)
                        .rotate(-45f) // Rotación para alinearlo horizontalmente
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = clase.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${clase.time} · ${clase.room}",
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )
            }
        }
    }
}