package com.prieto.tecsupfit.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPerfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la pantalla
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Mi perfil",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Card de Usuario Principal
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0F2EE))
                        .border(2.5.dp, Color(0xFF0F6D58), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AP",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F6D58)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Alexis Prieto",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "alexis.prieto@tecsup.edu.pe",
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Rol claro del usuario
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFE0F2EE))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Estudiante",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F6D58)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ficha Antropométrica
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DatoFisicoCard(valor = "1.75 m", etiqueta = "Estatura", modifier = Modifier.weight(1f))
            DatoFisicoCard(valor = "72 kg", etiqueta = "Peso", modifier = Modifier.weight(1f))
            DatoFisicoCard(valor = "Intermedio", etiqueta = "Nivel", modifier = Modifier.weight(1.2f))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Detalles del servicio/membresía
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Información de Membresía",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(14.dp))

                FilaDetalle(label = "Sede Habitual", valor = "Tecsup Campus Central")
                HorizontalDivider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(vertical = 10.dp))

                FilaDetalle(label = "Acceso", valor = "Gimnasio + Rutinas")
                HorizontalDivider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(vertical = 10.dp))

                FilaDetalle(label = "Estado", valor = "Activo")
            }
        }
    }
}

@Composable
fun DatoFisicoCard(
    valor: String,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F6D58)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = etiqueta,
                fontSize = 12.sp,
                color = Color(0xFF757575)
            )
        }
    }
}

@Composable
fun FilaDetalle(label: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = Color(0xFF757575)
        )
        Text(
            text = valor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E1E1E)
        )
    }
}