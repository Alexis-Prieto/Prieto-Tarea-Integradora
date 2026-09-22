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
    var listaReservas by remember { mutableStateOf(GymRepository.sampleReservations) }
    var reservaACancelar by remember { mutableStateOf<ReservationItem?>(null) }

    if (reservaACancelar != null) {
        AlertDialog(
            onDismissRequest = { reservaACancelar = null },
            title = { Text("Cancelar Reserva") },
            text = { Text("¿Estás seguro de que deseas cancelar tu reserva para ${reservaACancelar?.className}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        listaReservas = listaReservas.filter { it.id != reservaACancelar?.id }
                        reservaACancelar = null
                    }
                ) {
                    Text("Sí, cancelar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { reservaACancelar = null }) {
                    Text("Volver")
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Reservas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listaReservas) { reserva ->
                    TarjetaReserva(
                        reserva = reserva,
                        onCancelar = { reservaACancelar = reserva }
                    )
                }
            }
        }
    }
}
@Composable
fun TarjetaReserva(
    reserva: ReservationItem,
    onCancelar: () -> Unit
) {
    val esConfirmada = reserva.status == "Confirmada"

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reserva.className,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = reserva.status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (esConfirmada) Color(0xFF2E7D32) else Color.Gray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (esConfirmada) Color(0xFFE8F5E9) else Color(0xFFEEEEEE))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = reserva.schedule,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            if (esConfirmada) {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = onCancelar,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                ) {
                    Text("Cancelar Reserva")
                }
            }
        }
    }
}