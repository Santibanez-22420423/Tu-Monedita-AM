package com.example.tu_monedita.feature.dashboard.presentation.screen
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.components.MoneditaBottomNavigation
import com.example.tu_monedita.core.components.NavigationOption
import com.example.tu_monedita.core.designsystem.theme.*

private data class HomeTransaction(
    val id: String,
    val description: String,
    val amount: String,
    val isIncome: Boolean,
    val date: String
)

@Composable
fun HomeScreen(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onNavigateToNewTransaction: () -> Unit
){
    val transactions = listOf(
        HomeTransaction(
            "1", "Súper", "\$450.00", false, "12 nov. 2026"
        ),
        HomeTransaction(
            "2", "Pago de nómina", "\$2,000.00", true, "10 nov. 2026"
        )
    )

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
    ){ padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 90.dp
            )
        ) {

            item {
                // Encabezado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hola",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MoneditaTextStrong
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = MoneditaTextStrong,
                            modifier = Modifier.size(24.dp)
                        )

                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MoneditaExpenseRed)
                                .align(Alignment.TopEnd)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Saldo total
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MoneditaPrimary
                    ),
                    elevation = CardDefaults.cardElevation(0.dp)
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
                                text = "Tu saldo total",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )

                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "Ver saldo",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "\$8,450.00",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tarjetas de ingresos y gastos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryCard(
                        title = "Ingresos",
                        amount = "\$12,000.00",
                        color = MoneditaAvailableGreen,
                        modifier = Modifier.weight(1f)
                    )

                    SummaryCard(
                        title = "Gastos",
                        amount = "\$3,550.00",
                        color = MoneditaExpenseRed,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Gastos por categoría
                Text(
                    text = "Gastos por categoría",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MoneditaTextStrong
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomeDonutChart(
                        modifier = Modifier.size(110.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        HomeLegendItem(
                            color = Color(0xFFC5992E),
                            name = "Comida",
                            percent = "35%"
                        )

                        HomeLegendItem(
                            color = Color(0xFF78909C),
                            name = "Transporte",
                            percent = "20%"
                        )

                        HomeLegendItem(
                            color = Color(0xFF9575CD),
                            name = "Vivienda",
                            percent = "15%"
                        )

                        HomeLegendItem(
                            color = Color(0xFF64B5F6),
                            name = "Entretenimiento",
                            percent = "10%"
                        )

                        HomeLegendItem(
                            color = Color(0xFFBDBDBD),
                            name = "Otros",
                            percent = "20%"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Últimos movimientos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MoneditaTextStrong
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Lista de movimientos
            items(transactions) { transaction ->
                HomeTransactionItem(transaction)
            }
        }
    }
}

@Composable
private fun SummaryCard(
    title: String,
    amount: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Color(0xFFE0E0E0)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = title,
                color = color,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = amount,
                color = MoneditaTextStrong,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun HomeTransactionItem(
    transaction: HomeTransaction
) {
    val transactionColor = if (transaction.isIncome) {
        MoneditaAvailableGreen
    } else {
        MoneditaExpenseRed
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Color(0xFFE0E0E0)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(transactionColor.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (transaction.isIncome) {
                            Icons.Default.AccountBalanceWallet
                        } else {
                            Icons.Default.ShoppingCart
                        },
                        contentDescription = null,
                        tint = transactionColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = transaction.description,
                        color = MoneditaTextStrong,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = transaction.date,
                        fontSize = 12.sp,
                        color = MoneditaTextSecondary
                    )
                }
            }

            Text(
                text = if (transaction.isIncome) {
                    "+ ${transaction.amount}"
                } else {
                    "- ${transaction.amount}"
                },
                color = transactionColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun HomeLegendItem(
    color: Color,
    name: String,
    percent: String
) {
    Row(
        modifier = Modifier.width(155.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(9.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                fontSize = 12.sp,
                color = MoneditaTextStrong
            )
        }

        Text(
            text = percent,
            fontSize = 12.sp,
            color = MoneditaTextStrong,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun HomeDonutChart(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val strokeWidth = size.minDimension * 0.25f

        drawArc(
            color = Color(0xFFC5992E),
            startAngle = -90f,
            sweepAngle = 126f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = Color(0xFF78909C),
            startAngle = 36f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = Color(0xFF9575CD),
            startAngle = 108f,
            sweepAngle = 54f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = Color(0xFF64B5F6),
            startAngle = 162f,
            sweepAngle = 36f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = Color(0xFFBDBDBD),
            startAngle = 198f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}
