package com.example.tu_monedita.feature.reports.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*

private data class MonthlyReport(
    val month: String,
    val income: Int,
    val expenses: Int
)

@Composable
fun MonthlyComparisonScreen(
    onBackClick: () -> Unit = {}
) {
    val monthlyReports = listOf(
        MonthlyReport("Agosto", 12000, 8000),
        MonthlyReport("Septiembre", 14000, 9500),
        MonthlyReport("Octubre", 16000, 11000),
        MonthlyReport("Noviembre", 15000, 8500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // Encabezado
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = MoneditaTextStrong
                )
            }

            Text(
                text = "Comparación mensual",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Ingresos y gastos",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MoneditaTextStrong
        )

        Text(
            text = "Comparación de los últimos meses",
            fontSize = 14.sp,
            color = MoneditaTextSecondary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Leyenda
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MoneditaAvailableGreen)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Ingresos",
                fontSize = 12.sp,
                color = MoneditaTextSecondary
            )

            Spacer(modifier = Modifier.width(20.dp))

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MoneditaExpenseRed)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Gastos",
                fontSize = 12.sp,
                color = MoneditaTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Gráfica comparativa
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MoneditaSearchBackground
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    monthlyReports.forEach { report ->

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(14.dp)
                                        .height((report.income / 100).dp)
                                        .clip(
                                            RoundedCornerShape(
                                                topStart = 4.dp,
                                                topEnd = 4.dp
                                            )
                                        )
                                        .background(MoneditaAvailableGreen)
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Box(
                                    modifier = Modifier
                                        .width(14.dp)
                                        .height((report.expenses / 100).dp)
                                        .clip(
                                            RoundedCornerShape(
                                                topStart = 4.dp,
                                                topEnd = 4.dp
                                            )
                                        )
                                        .background(MoneditaExpenseRed)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = report.month.take(3),
                                fontSize = 11.sp,
                                color = MoneditaTextSecondary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resumen por mes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(12.dp))

        monthlyReports.forEach { report ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MoneditaSearchBackground
                )
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Text(
                        text = report.month,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoneditaTextStrong
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Ingresos",
                            color = MoneditaAvailableGreen,
                            fontSize = 13.sp
                        )

                        Text(
                            text = "$${report.income}.00",
                            color = MoneditaTextStrong,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Gastos",
                            color = MoneditaExpenseRed,
                            fontSize = 13.sp
                        )

                        Text(
                            text = "$${report.expenses}.00",
                            color = MoneditaTextStrong,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.LightGray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Balance",
                            color = MoneditaTextStrong,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "$${report.income - report.expenses}.00",
                            color = MoneditaAvailableGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
