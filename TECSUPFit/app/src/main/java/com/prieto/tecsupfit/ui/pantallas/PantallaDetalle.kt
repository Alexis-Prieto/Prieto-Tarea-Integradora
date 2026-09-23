package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prieto.tecsupfit.data.GymRepository

@Composable
fun PantallaDetalle(
    classId: Int,
    onVolver: () -> Unit = {},
    onReservar: (Int) -> Unit = {}
) {
    val clase = GymRepository.sampleClasses.find { it.id == classId }
        ?: GymRepository.sampleClasses.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onVolver() }
                .padding(vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Detalle de clase",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE0F2EE)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = Color(0xFF0F6D58),
                modifier = Modifier
                    .size(64.dp)
                    .rotate(-45f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = clase.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1E)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${clase.time} · ${clase.room} · ${clase.duration}",
            fontSize = 14.sp,
            color = Color(0xFF757575)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = clase.description,
            fontSize = 14.sp,
            color = Color(0xFF444444),
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${clase.availableSlots} de ${clase.totalSlots} cupos disponibles",
            fontSize = 14.sp,
            color = Color(0xFF555555)
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onReservar(clase.id) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F6D58)
            )
        ) {
            Text(
                text = "Reservar cupo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}