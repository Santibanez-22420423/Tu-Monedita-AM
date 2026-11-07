package com.example.tu_monedita.feature.reports.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

private data class ExpenseCategoryReport(
    val name: String,
    val amount: String,
    val percentage: Int,
    val color: Color
)

@Composable
fun ExpensesByCategoryScreen(
    onBackClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {}
) {
    val categories = listOf(
        ExpenseCategoryReport(
            "Comida", "$2,975.00", 35, Color(0xFFE57373)
        ),
        ExpenseCategoryReport(
            "Transporte", "$1,700.00", 20, Color(0xFFFFB74D)
        ),
        ExpenseCategoryReport(
            "Vivienda", "$1,275.00", 15, Color(0xFF64B5F6)
        ),
        ExpenseCategoryReport(
            "Entretenimiento", "$850.00", 10, Color(0xFFBA68C8)
        ),
        ExpenseCategoryReport(
            "Otros", "$1,700.00", 20, Color(0xFF90A4AE)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
                text = "Gastos por categoría",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Total de gastos
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MoneditaSearchBackground
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "Total de gastos",
                    color = MoneditaTextSecondary,
                    fontSize = 14.sp
                )

                Text(
                    text = "$8,500.00",
                    color = MoneditaTextStrong,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Noviembre 2026",
                    color = MoneditaTextSecondary,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Distribución por categoría",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(MoneditaSearchBackground)
                        .clickable {
                            onCategoryClick(category.name)
                        }
                        .padding(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(category.color)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = category.name,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.SemiBold,
                            color = MoneditaTextStrong
                        )

                        Text(
                            text = "${category.percentage}%",
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Barra de porcentaje
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(
                                    category.percentage / 100f
                                )
                                .fillMaxHeight()
                                .clip(CircleShape)
                                .background(category.color)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = category.amount,
                        fontSize = 14.sp,
                        color = MoneditaTextSecondary
                    )
                }
            }
        }
    }
}
