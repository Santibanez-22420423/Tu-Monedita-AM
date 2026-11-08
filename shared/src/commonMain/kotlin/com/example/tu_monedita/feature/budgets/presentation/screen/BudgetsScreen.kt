package com.example.tu_monedita.feature.budgets.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import com.example.tu_monedita.core.components.MoneditaBottomNavigation
import com.example.tu_monedita.core.components.NavigationOption
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ReceiptLong
import com.example.tu_monedita.core.designsystem.component.MoneditaHeader
import com.example.tu_monedita.core.designsystem.images.MoneditaExpression
import com.example.tu_monedita.core.designsystem.theme.*


data class BudgetItem(
    val id: String,
    val name: String,
    val spent: Double,
    val limit: Double,
    val icon: ImageVector,
    val iconColor: Color,
    val progressColor: Color
) {
    val percentage: Int
        get() = if (limit > 0) ((spent / limit) * 100).toInt().coerceIn(0, 100) else 0
}

private fun formatAmount(amount: Double, decimals: Int = 0): String {
    val factor = if (decimals == 2) 100L else 1L
    val rounded = kotlin.math.round(amount * factor).toLong()

    val integerPart = rounded / factor
    val decimalPart = kotlin.math.abs(rounded % factor)

    val formattedInteger = integerPart.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return if (decimals == 2) {
        "$formattedInteger.${decimalPart.toString().padStart(2, '0')}"
    } else {
        formattedInteger
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun main_Budgets(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onBudgetClick: (BudgetItem) -> Unit = {},
    onFilterClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onBottomNavClick: (Int) -> Unit = {}
) {

    var currentScreen by remember {
        mutableStateOf(BudgetScreen.MAIN)
    }

    var selectedBudget by remember {
        mutableStateOf<BudgetItem?>(null)
    }

    val budgets = remember {
        listOf(
            BudgetItem(
                id = "1",
                name = "Alimentación",
                spent = 1250.0,
                limit = 2000.0,
                icon = Icons.Default.Restaurant,
                iconColor = MoneditaFood,
                progressColor = MoneditaFood
            ),
            BudgetItem(
                id = "2",
                name = "Transporte",
                spent = 400.0,
                limit = 800.0,
                icon = Icons.Default.DirectionsCar,
                iconColor = MoneditaTransport,
                progressColor = MoneditaTransport
            ),
            BudgetItem(
                id = "3",
                name = "Renta",
                spent = 900.0,
                limit = 1200.0,
                icon = Icons.Default.Home,
                iconColor = MoneditaRent,
                progressColor = MoneditaRent
            ),
            BudgetItem(
                id = "4",
                name = "Ropa",
                spent = 200.0,
                limit = 600.0,
                icon = Icons.Default.Checkroom,
                iconColor = MoneditaClothing,
                progressColor = MoneditaClothing
            )
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var showFilterMenu by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf("Todos") }
    val totalBudgeted = budgets.sumOf { it.limit }
    val totalSpent = budgets.sumOf { it.spent }

    Scaffold(
        containerColor = Color.White,

        // Navegación inferior compartida
        bottomBar = {
            MoneditaBottomNavigation(
                selectedItem = selectedItem,
                dynamicOptions = dynamicOptions,
                onItemClick = onItemClick,
                onMoreClick = onMoreClick
            )
        },

        // El botón + solamente aparece en la pantalla principal
        floatingActionButton = {
            if (currentScreen == BudgetScreen.MAIN) {
                FloatingActionButton(
                    onClick = {
                        currentScreen = BudgetScreen.CREATE
                    },
                    containerColor = MoneditaAccent,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Crear presupuesto"
                    )
                }
            }
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {

            when (currentScreen) {

                // ===================== PANTALLA PRINCIPAL =====================
                BudgetScreen.MAIN -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        // Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoneditaHeader(expression = MoneditaExpression.HAPPY)
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE2E8F0)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Perfil",
                                    tint = MoneditaTextSecondary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        // Título
                        Text(
                            text = "Presupuestos",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Card Resumen mensual
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MoneditaDarkBlue)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = "Resumen mensual",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Total presupuestado",
                                            color = Color.White.copy(alpha = 0.75f),
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "$${formatAmount(totalBudgeted, 2)}",
                                            color = Color.White,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    // Divisor vertical
                                    Box(
                                        modifier = Modifier
                                            .width(1.dp)
                                            .height(48.dp)
                                            .background(Color.White.copy(alpha = 0.3f))
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 16.dp)
                                    ) {
                                        Text(
                                            text = "Total gastado",
                                            color = Color.White.copy(alpha = 0.75f),
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "$${formatAmount(totalSpent, 2)}",
                                            color = Color.White,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Barra de búsqueda
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                modifier = Modifier.weight(1f),
                                placeholder = {
                                    Text(
                                        "Buscar presupuesto...",
                                        color = MoneditaTextSecondary,
                                        fontSize = 14.sp
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = null,
                                        tint = MoneditaTextSecondary
                                    )
                                },
                                shape = RoundedCornerShape(24.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedContainerColor = MoneditaSearchBackground,
                                    unfocusedContainerColor = MoneditaSearchBackground
                                ),
                                singleLine = true
                            )
                            Box {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(MoneditaSearchBackground)
                                        .clickable {
                                            showFilterMenu = true
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Default.Tune,
                                        contentDescription = "Filtros",
                                        tint = MoneditaTextSecondary
                                    )
                                }

                                DropdownMenu(
                                    expanded = showFilterMenu,
                                    onDismissRequest = { showFilterMenu = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Todos") },
                                        onClick = {
                                            selectedFilter = "Todos"
                                            showFilterMenu = false
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = { Text("Con gastos") },
                                        onClick = {
                                            selectedFilter = "Con gastos"
                                            showFilterMenu = false
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = { Text("Sin gastos") },
                                        onClick = {
                                            selectedFilter = "Sin gastos"
                                            showFilterMenu = false
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = { Text("Presupuestos excedidos") },
                                        onClick = {
                                            selectedFilter = "Excedidos"
                                            showFilterMenu = false
                                        }
                                    )
                                }
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        val filteredBudgets = budgets.filter { budget ->
                            when (selectedFilter) {
                                "Con gastos" -> budget.spent > 0
                                "Sin gastos" -> budget.spent == 0.0
                                "Excedidos" -> budget.spent > budget.limit
                                else -> true
                            }
                        }
                        // Lista de presupuestos
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filteredBudgets) { budget ->
                                BudgetListItem(
                                    budget = budget,
                                    onClick = {
                                        selectedBudget = budget
                                        currentScreen = BudgetScreen.DETAILS
                                    }
                                )
                            }
                        }
                    }
                }

                // ===================== DETALLE =====================
                BudgetScreen.DETAILS -> {
                    selectedBudget?.let { budget ->
                        Details_Budgets(
                            categoryName = budget.name,
                            spent = budget.spent,
                            limit = budget.limit,
                            icon = budget.icon,
                            iconColor = budget.iconColor,
                            progressColor = budget.progressColor,
                            onBackClick = {
                                currentScreen = BudgetScreen.MAIN
                            },
                            onEditClick = {
                                currentScreen = BudgetScreen.EDIT
                            }
                        )
                    }
                }

                // ===================== CREAR PRESUPUESTO =====================
                BudgetScreen.CREATE -> {
                    create_Budgets(
                        onBackClick = {
                            currentScreen = BudgetScreen.MAIN
                        },
                        onSaveClick = { category, limit, month, year ->
                            // Aquí posteriormente puedes guardar el presupuesto
                            currentScreen = BudgetScreen.MAIN
                        }
                    )
                }
                    // ===================== EDITAR PRESUPUESTO =====================
                BudgetScreen.EDIT -> {
                    selectedBudget?.let { budget ->
                        edit_Budgets(
                            initialCategory = budget.name,
                            initialLimit = formatAmount(budget.limit),
                            initialMonth = "Noviembre",
                            initialYear = "2026",
                            onBackClick = {
                                currentScreen = BudgetScreen.DETAILS
                            },
                            onSaveClick = { category, limit, month, year ->
                                // Aquí posteriormente actualizas el presupuesto
                                currentScreen = BudgetScreen.DETAILS
                            },
                            onDeleteClick = {
                                currentScreen = BudgetScreen.MAIN
                                selectedBudget = null
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun BudgetListItem(
    budget: BudgetItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(budget.iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = budget.icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = budget.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MoneditaTextStrong
                    )
                    Text(
                        text = "$${formatAmount(budget.spent)} / $${formatAmount(budget.limit)}",
                        fontSize = 13.sp,
                        color = MoneditaTextSecondary
                    )
                }
                Text(
                    text = "${budget.percentage}%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MoneditaTextSecondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MoneditaTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Progress bar
            LinearProgressIndicator(
                progress = { budget.percentage / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = budget.progressColor,
                trackColor = Color(0xFFE2E8F0),
                strokeCap = StrokeCap.Round
            )
        }
    }
}

private enum class BudgetScreen {
    MAIN,
    DETAILS,
    CREATE,
    EDIT
}
