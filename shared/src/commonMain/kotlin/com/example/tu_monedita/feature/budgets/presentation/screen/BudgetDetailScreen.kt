package com.example.tu_monedita.feature.budgets.presentation.screen
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import com.example.tu_monedita.core.designsystem.theme.*
data class BudgetMovement(
    val title: String,
    val date: String,
    val amount: Double,
    val isExpense: Boolean
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
fun Details_Budgets(
    categoryName: String = "Alimentación",
    period: String = "Noviembre 2024",
    spent: Double = 1250.0,
    limit: Double = 2000.0,
    icon: ImageVector = Icons.Default.Restaurant,
    iconColor: Color = MoneditaFood,
    progressColor: Color = MoneditaFood,
    movements: List<BudgetMovement> = listOf(
        BudgetMovement("Supermercado", "12 nov 2024", 320.0, true),
        BudgetMovement("Comida", "10 nov 2024", 180.0, true),
        BudgetMovement("Pago de trabajo", "05 nov 2024", 500.0, false)
    ),
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    val percentage = if (limit > 0) ((spent / limit) * 100).toInt().coerceIn(0, 100) else 0
    val remaining = (limit - spent).coerceAtLeast(0.0)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalle de presupuesto",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = MoneditaTextStrong
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MoneditaTextStrong
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Header categoría
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = categoryName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoneditaTextStrong
                    )
                    Text(
                        text = period,
                        fontSize = 14.sp,
                        color = MoneditaTextStrong
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Circular progress
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(160.dp)) {
                    val strokeWidth = 14.dp.toPx()
                    // Track
                    drawArc(
                        color = Color(0xFFE2E8F0),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                    // Progress
                    drawArc(
                        color = progressColor,
                        startAngle = -90f,
                        sweepAngle = 360f * (percentage / 100f),
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$percentage%",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoneditaTextStrong
                    )
                    Text(
                        text = "utilizado",
                        fontSize = 13.sp,
                        color = MoneditaTextStrong
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Monto gastado / límite
            Text(
                text = "$${formatAmount(spent)} / $${formatAmount(limit)}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Disponible
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.AccountBalanceWallet,
                    contentDescription = null,
                    tint = MoneditaAvailableGreen,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Te quedan $${formatAmount(remaining)} disponibles",
                    fontSize = 14.sp,
                    color = MoneditaAvailableGreen,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Cards resumen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MoneditaLightGrayCard)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Presupuesto mensual",
                            fontSize = 12.sp,
                            color = MoneditaTextStrong
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$${formatAmount(limit)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong
                        )
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MoneditaLightGrayCard)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Gastos registrados",
                            fontSize = 12.sp,
                            color = MoneditaTextStrong
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$${formatAmount(spent)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Últimos movimientos
            Text(
                text = "Últimos movimientos",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )

            Spacer(modifier = Modifier.height(12.dp))

            movements.forEach { movement ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                if (movement.isExpense) Color(0xFFFEE2E2)
                                else Color(0xFFDCFCE7)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            if (movement.isExpense) Icons.Default.ArrowUpward
                            else Icons.Default.ArrowDownward,
                            contentDescription = null,
                            tint = if (movement.isExpense) MoneditaExpenseRed else MoneditaAvailableGreen,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = movement.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = MoneditaTextStrong
                        )
                        Text(
                            text = movement.date,
                            fontSize = 12.sp,
                            color = MoneditaTextStrong
                        )
                    }
                    Text(
                        text = "$${formatAmount(movement.amount, 2)}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MoneditaTextStrong
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
