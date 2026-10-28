package com.example.tu_monedita.feature.transactions.presentation.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*


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
fun detail_Transaction(
    title: String = "Sueldo",
    subtitle: String = "Trabajo",
    amount: Double = 1500.00,
    isIncome: Boolean = true,
    category: String = "Salario",
    date: String = "12 nov 2024 · 10:30",
    description: String = "Pago de trabajo mensual",
    createdAt: String = "12 nov 2024 · 10:30",
    lastModified: String = "12 nov 2024 · 10:30",
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalle del movimiento",
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
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Card principal con título y monto
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isIncome) MoneditaLightGreen else Color(0xFFFEE2E2)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(if (isIncome) MoneditaAvailableGreen else MoneditaExpenseRed),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    if (isIncome) Icons.Default.Work else Icons.Default.Restaurant,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = title,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MoneditaTextStrong
                                )
                                Text(
                                    text = subtitle,
                                    fontSize = 14.sp,
                                    color = MoneditaTextSecondary
                                )
                            }
                        }
                        // Badge INGRESO / GASTO
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isIncome) Color(0xFFBBF7D0) else Color(0xFFFECACA)
                        ) {
                            Text(
                                text = if (isIncome) "INGRESO" else "GASTO",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isIncome) MoneditaAvailableGreen else MoneditaExpenseRed
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isIncome) "+$${formatAmount(amount, 2)}"
                        else "-$${formatAmount(amount, 2)}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isIncome) MoneditaAvailableGreen else MoneditaExpenseRed
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detalles
            DetailRow(
                icon = Icons.Default.Work,
                iconBg = MoneditaInfoBackground,
                iconTint = MoneditaPrimary,
                label = "Categoría",
                value = category
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailRow(
                icon = Icons.Default.CalendarToday,
                iconBg = MoneditaInfoBackground,
                iconTint = MoneditaPrimary,
                label = "Fecha",
                value = date
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailRow(
                icon = Icons.Outlined.Description,
                iconBg = MoneditaInfoBackground,
                iconTint = MoneditaPrimary,
                label = "Descripción",
                value = description
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailRow(
                icon = Icons.Outlined.Schedule,
                iconBg = MoneditaInfoBackground,
                iconTint = MoneditaPrimary,
                label = "Creado el",
                value = createdAt
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailRow(
                icon = Icons.Outlined.Update,
                iconBg = MoneditaInfoBackground,
                iconTint = MoneditaPrimary,
                label = "Última modificación",
                value = lastModified
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón Editar
            Button(
                onClick = onEditClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .offset(y = (-130).dp),

                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoneditaPrimary
                )
            ) {
                Text(
                    text = "Editar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón Eliminar
            OutlinedButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .offset(y = (-130).dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MoneditaExpenseRed
                )
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Eliminar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = label,
                fontSize = 13.sp,
                color = MoneditaTextSecondary
            )
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MoneditaTextStrong
            )
        }
    }
}
