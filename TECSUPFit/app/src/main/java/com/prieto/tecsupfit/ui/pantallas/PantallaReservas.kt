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
    val listaReservas = GymRepository.sampleReservations
    var reservaACancelar by remember { mutableStateOf<ReservationItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
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
                Text(
                    text = "No tienes reservas activas",
                    color = Color(0xFF757575),
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
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

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            if (esConfirmada) {
                Box(
                    modifier = Modifier
                        .width(5.dp)
                        .fillMaxHeight()
                        .background(Color(0xFF0F6D58))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = reserva.className,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )

                    if (esConfirmada) {
                        TextButton(
                            onClick = onCancelarClick,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "Cancelar",
                                color = Color(0xFFD32F2F),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = reserva.schedule,
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (esConfirmada) Color(0xFFE0F2EE) else Color(0xFFE5E5E5))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = reserva.status,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (esConfirmada) Color(0xFF0F6D58) else Color(0xFF757575)
                    )
                }
            }
        }
    }
}