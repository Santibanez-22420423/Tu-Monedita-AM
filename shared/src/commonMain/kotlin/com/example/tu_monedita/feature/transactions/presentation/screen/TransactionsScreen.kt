package com.example.tu_monedita.feature.transactions.presentation.screen
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*

data class TransactionItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val amount: Double,
    val isIncome: Boolean,
    val icon: ImageVector,
    val iconBgColor: Color
)
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
fun main_Transaction(
    onAddClick: () -> Unit = {},
    onTransactionClick: (TransactionItem) -> Unit = {},
    onFilterClick: () -> Unit = {},
    onBottomNavClick: (Int) -> Unit = {}
) {
    val transactions = remember {
        listOf(
            TransactionItem(
                id = "1",
                title = "Sueldo",
                subtitle = "Trabajo",
                date = "12 nov 2024 · 10:30",
                amount = 1500.00,
                isIncome = true,
                icon = Icons.Default.Work,
                iconBgColor = Color(0xFF16A34A)
            ),
            TransactionItem(
                id = "2",
                title = "Comida",
                subtitle = "Supermercado",
                date = "11 nov 2024 · 14:20",
                amount = 320.00,
                isIncome = false,
                icon = Icons.Default.Restaurant,
                iconBgColor = Color(0xFFA81919)            ),
            TransactionItem(
                id = "3",
                title = "Renta",
                subtitle = "Oficina",
                date = "10 nov 2024 · 09:00",
                amount = 900.00,
                isIncome = false,
                icon = Icons.Default.Checkroom,
                iconBgColor = Color(0xFF7C3AED)
            ),
            TransactionItem(
                id = "4",
                title = "Transporte",
                subtitle = "Recarga tarjeta",
                date = "08 nov 2024 · 18:45",
                amount = 120.00,
                isIncome = false,
                icon = Icons.Default.DirectionsBus,
                iconBgColor = Color(0xFF16A34A)
            ),
            TransactionItem(
                id = "5",
                title = "Freelance",
                subtitle = "Trabajo",
                date = "07 nov 2024 · 16:10",
                amount = 700.00,
                isIncome = true,
                icon = Icons.Default.Work,
                iconBgColor = Color(0xFF16A34A)
            )
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var selectedBottomNav by remember { mutableStateOf(1) }
    var showFilter by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = MoneditaTextSecondary
            ) {
                NavigationBarItem(
                    selected = selectedBottomNav == 0,
                    onClick = {
                        selectedBottomNav = 0
                        onBottomNavClick(0)
                    },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MoneditaAccent,
                        selectedTextColor = MoneditaAccent,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 1,
                    onClick = {
                        selectedBottomNav = 1
                        onBottomNavClick(1)
                    },
                    icon = { Icon(Icons.Default.ReceiptLong, contentDescription = "Movimientos") },
                    label = { Text("Movimientos", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MoneditaAccent,
                        selectedTextColor = MoneditaAccent,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 2,
                    onClick = {
                        selectedBottomNav = 2
                        onBottomNavClick(2)
                    },
                    icon = { Icon(Icons.Outlined.ShoppingBag, contentDescription = "Presupuestos") },
                    label = { Text("Presupuestos", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MoneditaAccent,
                        selectedTextColor = MoneditaAccent,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 3,
                    onClick = {
                        selectedBottomNav = 3
                        onBottomNavClick(3)
                    },
                    icon = { Icon(Icons.Outlined.Apps, contentDescription = "Más") },
                    label = { Text("Más", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MoneditaAccent,
                        selectedTextColor = MoneditaAccent,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MoneditaPrimary,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFEF08A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("😊", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "TU MONEDITA",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MoneditaTextSecondary,
                        letterSpacing = 0.5.sp
                    )
                }
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
                text = "Movimientos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card Balance del mes
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
                        text = "Balance del mes",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$840.00",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Ingresos
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.ArrowUpward,
                                        contentDescription = null,
                                        tint = MoneditaAvailableGreen,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Ingresos",
                                        fontSize = 12.sp,
                                        color = MoneditaTextSecondary
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$2,500.00",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MoneditaTextStrong
                                )
                            }
                        }
                        // Gastos
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.ArrowDownward,
                                        contentDescription = null,
                                        tint = MoneditaExpenseRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Gastos",
                                        fontSize = 12.sp,
                                        color = MoneditaTextSecondary
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$1,660.00",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MoneditaTextStrong
                                )
                            }
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
                            "Buscar movimiento...",
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
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MoneditaSearchBackground)
                        .clickable {
                            showFilter = true
                            onFilterClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Tune,
                        contentDescription = "Filtros",
                        tint = MoneditaTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de movimientos
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionListItem(
                        transaction = transaction,
                        onClick = { onTransactionClick(transaction) }
                    )
                }
            }
        }
    }
    // Modal de filtros
    if (showFilter) {
        ModalBottomSheet(
            onDismissRequest = {
                showFilter = false
            },
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            containerColor = Color.White,
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp
            )
        ) {
            TransactionScreen5(
                onDismiss = {
                    showFilter = false
                },
                onApply = { type, category, from, to ->
                    // Aquí aplicarás los filtros a los movimientos
                    showFilter = false
                },
                onClearFilters = {
                    // Aquí puedes restablecer los filtros
                }
            )
        }
    }
}

@Composable
private fun TransactionListItem(
    transaction: TransactionItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(transaction.iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )
            Text(
                text = transaction.subtitle,
                fontSize = 13.sp,
                color = MoneditaTextSecondary
            )
            Text(
                text = transaction.date,
                fontSize = 12.sp,
                color = MoneditaTextSecondary.copy(alpha = 0.8f)
            )
        }
        Text(
            text = if (transaction.isIncome) "+$${formatAmount(transaction.amount, 2)}"
            else "-$${formatAmount(transaction.amount, 2)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (transaction.isIncome) MoneditaAvailableGreen else MoneditaExpenseRed
        )
    }

}
