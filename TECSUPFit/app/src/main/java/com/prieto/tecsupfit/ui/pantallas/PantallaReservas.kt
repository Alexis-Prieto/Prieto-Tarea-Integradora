package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prieto.tecsupfit.data.GymRepository
import com.prieto.tecsupfit.data.ReservationItem

@Composable
fun PantallaReservas() {
    // Apuntamos directamente a la lista del repositorio para que la eliminacion sea permanente entre pantallas
    val listaReservas = GymRepository.sampleReservations

    // Estado para controlar el diálogo de confirmación
    var reservaACancelar by remember { mutableStateOf<ReservationItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Mis reservas",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1E),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        if (listaReservas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No tienes reservas activas", color = Color.Gray)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listaReservas) { reserva ->
                    TarjetaReserva(
                        reserva = reserva,
                        onCancelarClick = { reservaACancelar = reserva }
                    )
                }
            }
        }
    }

    // Modal AlertDialog de Confirmación
    reservaACancelar?.let { reserva ->
        AlertDialog(
            onDismissRequest = { reservaACancelar = null },
            title = { Text(text = "Cancelar reserva", fontWeight = FontWeight.Bold) },
            text = { Text(text = "¿Estás seguro de que deseas cancelar tu reserva de ${reserva.className}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        listaReservas.remove(reserva)
                        reservaACancelar = null
                    }
                ) {
                    Text("Sí, cancelar", color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { reservaACancelar = null }) {
                    Text("No", color = Color.Gray)
                }
            }
        )
    }
}

@Composable
fun TarjetaReserva(
    reserva: ReservationItem,
    onCancelarClick: () -> Unit
) {
    val esConfirmada = reserva.status == "Confirmada"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF2F2F2))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            if (esConfirmada) {
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .fillMaxHeight()
                        .background(Color(0xFF0F6D58))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = reserva.className,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )

                    // Solo permite cancelar reservas que están "Confirmadas"
                    if (esConfirmada) {
                        TextButton(onClick = onCancelarClick) {
                            Text(
                                text = "Cancelar",
                                color = Color(0xFFD32F2F),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = reserva.schedule,
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (esConfirmada) Color(0xFFE0F2EE) else Color(0xFFE5E5E5))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = reserva.status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (esConfirmada) Color(0xFF0F6D58) else Color(0xFF757575)
                    )
                }
            }
        }
    }
}