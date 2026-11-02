package com.example.tu_monedita.feature.reports.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*

@Composable
fun CategoryReportScreen(
    categoryName: String = "Comida",
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = MoneditaTextStrong
                )
            }

            Text(
                text = "Detalle de categoría",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MoneditaSearchBackground
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = categoryName,
                    tint = MoneditaPrimary,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = categoryName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoneditaTextStrong
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Total gastado este mes",
                    fontSize = 14.sp,
                    color = MoneditaTextSecondary
                )

                Text(
                    text = "\$2,975.00",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoneditaExpenseRed
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resumen",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(12.dp))

        ReportDetailRow(
            title = "Porcentaje de gastos",
            value = "35%"
        )

        ReportDetailRow(
            title = "Número de movimientos",
            value = "12"
        )

        ReportDetailRow(
            title = "Promedio por movimiento",
            value = "\$247.92"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Periodo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(12.dp))

        ReportDetailRow(
            title = "Mes consultado",
            value = "Noviembre 2026"
        )
    }
}

@Composable
private fun ReportDetailRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = MoneditaTextSecondary,
            fontSize = 14.sp
        )

        Text(
            text = value,
            color = MoneditaTextStrong,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    HorizontalDivider(color = MoneditaSearchBackground)
}
