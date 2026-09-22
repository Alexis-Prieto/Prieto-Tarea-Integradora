package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prieto.tecsupfit.data.GymRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    classId: Int,
    onVolver: () -> Unit,
    onReservar: (Int) -> Unit
) {
    val clase = GymRepository.sampleClasses.find { it.id == classId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Clase") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (clase == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Clase no encontrada")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = clase.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${clase.time} (${clase.duration})", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = clase.room, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Cupos disponibles: ${clase.availableSlots} de ${clase.totalSlots}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (clase.availableSlots > 0) Color(0xFF2E7D32) else Color.Red
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Descripción",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = clase.description,
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )
                }
                Button(
                    onClick = { onReservar(clase.id) },
                    enabled = clase.availableSlots > 0,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (clase.availableSlots > 0) "Reservar Cupo" else "Sin Cupos",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}