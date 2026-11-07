
package com.example.tu_monedita.feature.reports.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.components.MoneditaBottomNavigation
import com.example.tu_monedita.core.components.NavigationOption
import com.example.tu_monedita.core.designsystem.component.MoneditaHeader
import com.example.tu_monedita.core.designsystem.images.MoneditaExpression
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
    // Pantalla actual
    var currentScreen by remember {
        mutableStateOf(ReportScreen.MAIN)
    }

    // Categoría seleccionada
    var selectedCategory by remember {
        mutableStateOf("Comida")
    }

    // Pantalla desde donde se abrió el detalle
    var categoryOrigin by remember {
        mutableStateOf(ReportScreen.MAIN)
    }

    // Selector de gastos e ingresos
    var isExpense by remember {
        mutableStateOf(true)
    }

    val expenseCategories = listOf(
        Triple("Comida", "35%", MoneditaFood),
        Triple("Transporte", "20%", MoneditaTransport),
        Triple("Vivienda", "15%", MoneditaRent),
        Triple("Entretenimiento", "10%", MoneditaAccent),
        Triple("Otros", "20%", MoneditaTextSecondary)
    )

    val incomeCategories = listOf(
        Triple("Salario", "80%", MoneditaAvailableGreen),
        Triple("Inversiones", "15%", MoneditaInfoBlue),
        Triple("Otros", "5%", MoneditaTextSecondary)
    )

    val currentList = if (isExpense) {
        expenseCategories
    } else {
        incomeCategories
    }

    // Scaffold compartido para TODAS las pantallas.
    // El menú inferior ya no desaparece al navegar.
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
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MoneditaBackground)
        ) {

            when (currentScreen) {

                // =====================================
                // PANTALLA PRINCIPAL DE REPORTES
                // =====================================

                ReportScreen.MAIN -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 20.dp)
                    ) {

                        // Encabezado
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoneditaHeader(
                                expression = MoneditaExpression.ANGRY
                            )

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MoneditaProfileBackground),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Perfil",
                                    tint = MoneditaTextSecondary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        Text(
                            text = "Reportes",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Selector de gastos e ingresos
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(MoneditaSearchBackground),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf("Gastos", "Ingresos").forEachIndexed {
                                    index, title ->

                                val selected = isExpense == (index == 0)

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(
                                            if (selected) {
                                                if (title == "Gastos") MoneditaExpenseRed
                                                else MoneditaAvailableGreen
                                            } else {
                                                Color.Transparent
                                            }
                                        )
                                        .clickable {
                                            isExpense = index == 0
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = title,
                                        color = if (selected) {
                                            MoneditaWhite
                                        } else {
                                            MoneditaTextSecondary
                                        },
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Resumen del mes
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

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MoneditaWhite
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = if (isExpense) {
                                        "Total de gastos"
                                    } else {
                                        "Total de ingresos"
                                    },
                                    color = MoneditaTextSecondary,
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = if (isExpense) {
                                        "$8,500.00"
                                    } else {
                                        "$15,000.00"
                                    },
                                    color = MoneditaTextStrong,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    listOf(
                                        "Semana 1",
                                        "Semana 2",
                                        "Semana 3",
                                        "Semana 4"
                                    ).forEach { week ->
                                        Text(
                                            text = week,
                                            color = MoneditaTextSecondary,
                                            fontSize = 12.sp
                                        )
                                    }
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
                                                    if (isExpense) {
                                                        MoneditaExpenseRed
                                                    } else {
                                                        MoneditaAvailableGreen
                                                    }
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
                                text = if (isExpense) {
                                    "Gastos por categoría"
                                } else {
                                    "Ingresos por categoría"
                                },
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MoneditaTextStrong
                            )

                            if (isExpense) {
                                Text(
                                    text = "Ver detalle",
                                    color = MoneditaPrimary,
                                    fontSize = 13.sp,
                                    modifier = Modifier.clickable {
                                        currentScreen =
                                            ReportScreen.EXPENSES_BY_CATEGORY
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Lista de categorías
                        currentList.forEach { category ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MoneditaWhite)
                                    .clickable {
                                        if (isExpense) {
                                            selectedCategory = category.first
                                            categoryOrigin = ReportScreen.MAIN
                                            currentScreen =
                                                ReportScreen.CATEGORY_DETAIL
                                        }
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

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Comparación mensual
                        ReportOption(
                            title = "Comparación mensual",
                            description = "Compara tus ingresos y gastos",
                            icon = Icons.Default.CompareArrows,
                            onClick = {
                                currentScreen =
                                    ReportScreen.MONTHLY_COMPARISON
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                // =====================================
                // GASTOS POR CATEGORÍA
                // =====================================

                ReportScreen.EXPENSES_BY_CATEGORY -> {

                    ExpensesByCategoryScreen(
                        onBackClick = {
                            currentScreen = ReportScreen.MAIN
                        },
                        onCategoryClick = { category ->
                            selectedCategory = category

                            categoryOrigin =
                                ReportScreen.EXPENSES_BY_CATEGORY

                            currentScreen =
                                ReportScreen.CATEGORY_DETAIL
                        }
                    )
                }

                // =====================================
                // DETALLE DE CATEGORÍA
                // =====================================

                ReportScreen.CATEGORY_DETAIL -> {

                    CategoryReportScreen(
                        categoryName = selectedCategory,
                        onBackClick = {
                            currentScreen = categoryOrigin
                        }
                    )
                }

                // =====================================
                // COMPARACIÓN MENSUAL
                // =====================================

                ReportScreen.MONTHLY_COMPARISON -> {

                    MonthlyComparisonScreen(
                        onBackClick = {
                            currentScreen = ReportScreen.MAIN
                        }
                    )
                }
            }
        }
    }
}

// =====================================
// COMPONENTE DE OPCIÓN DE REPORTE
// =====================================

@Composable
private fun ReportOption(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoneditaWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
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

            Column(
                modifier = Modifier.weight(1f)
            ) {
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
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MoneditaTextSecondary
            )
        }
    }
}

// =====================================
// ESTADOS DE NAVEGACIÓN
// =====================================

private enum class ReportScreen {
    MAIN,
    EXPENSES_BY_CATEGORY,
    CATEGORY_DETAIL,
    MONTHLY_COMPARISON
}
