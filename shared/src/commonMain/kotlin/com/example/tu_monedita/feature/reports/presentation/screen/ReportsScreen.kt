package com.example.tu_monedita.feature.reports.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.components.MoneditaBottomNavigation
import com.example.tu_monedita.core.components.NavigationOption
import com.example.tu_monedita.core.designsystem.theme.*

@Composable
fun main_Reports(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onCategoryReportClick: () -> Unit = {},
    onExpensesByCategoryClick: () -> Unit = {},
    onMonthlyComparisonClick: () -> Unit = {},
    onBottomNavClick: (Int) -> Unit = {}
) {
    var isExpense by remember { mutableStateOf(true) }

    val expenseCategories = listOf(
        Triple("Comida", "35%", Color(0xFFE57373)),
        Triple("Transporte", "20%", Color(0xFFFFB74D)),
        Triple("Vivienda", "15%", Color(0xFF64B5F6)),
        Triple("Entretenimiento", "10%", Color(0xFFBA68C8)),
        Triple("Otros", "20%", Color(0xFF90A4AE))
    )

    val incomeCategories = listOf(
        Triple("Salario", "80%", MoneditaAvailableGreen),
        Triple("Inversiones", "15%", Color(0xFF4DB6AC)),
        Triple("Otros", "5%", Color(0xFF90A4AE))
    )

    val currentList = if (isExpense) expenseCategories else incomeCategories

    Scaffold(
        containerColor = MoneditaBackground,
        bottomBar = {
            MoneditaBottomNavigation(
                selectedItem = selectedItem,
                dynamicOptions = dynamicOptions,
                onItemClick = onItemClick,
                onMoreClick = onMoreClick
            )
        }
    )  { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Reportes",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector Gastos e Ingresos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MoneditaSearchBackground),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Gastos", "Ingresos").forEachIndexed { index, title ->
                    val selected = isExpense == (index == 0)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50.dp))
                            .background(
                                if (selected) MoneditaPrimary
                                else Color.Transparent
                            )
                            .clickable {
                                isExpense = index == 0
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (selected) Color.White
                            else MoneditaTextSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Resumen del mes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )

            Text(
                text = "Noviembre 2026",
                fontSize = 14.sp,
                color = MoneditaTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Resumen de cantidades
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
                    Text(
                        text = if (isExpense) "Total de gastos" else "Total de ingresos",
                        color = MoneditaTextSecondary,
                        fontSize = 14.sp
                    )

                    Text(
                        text = if (isExpense) "$8,500.00" else "$15,000.00",
                        color = MoneditaTextStrong,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Semana 1",
                            color = MoneditaTextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Semana 2",
                            color = MoneditaTextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Semana 3",
                            color = MoneditaTextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Semana 4",
                            color = MoneditaTextSecondary,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val values = listOf(35, 55, 75, 45)

                        values.forEach { value ->
                            Box(
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(value.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 6.dp,
                                            topEnd = 6.dp
                                        )
                                    )
                                    .background(
                                        if (isExpense) MoneditaExpenseRed
                                        else MoneditaAvailableGreen
                                    )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Categorías
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isExpense) "Gastos por categoría"
                    else "Ingresos por categoría",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoneditaTextStrong
                )

                Text(
                    text = "Ver detalle",
                    color = MoneditaPrimary,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        onExpensesByCategoryClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(currentList) { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MoneditaSearchBackground)
                            .clickable {
                                onCategoryReportClick()
                            }
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(category.third)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = category.first,
                            modifier = Modifier.weight(1f),
                            color = MoneditaTextStrong,
                            fontSize = 14.sp
                        )

                        Text(
                            text = category.second,
                            color = MoneditaTextStrong,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    ReportOption(
                        title = "Comparación mensual",
                        description = "Compara tus ingresos y gastos",
                        icon = Icons.Default.CompareArrows,
                        onClick = onMonthlyComparisonClick
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ReportOption(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoneditaSearchBackground
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MoneditaPrimary
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MoneditaTextStrong
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = MoneditaTextSecondary
                )
            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MoneditaTextSecondary
            )
        }
    }
}
