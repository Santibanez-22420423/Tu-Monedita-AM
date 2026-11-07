package com.example.tu_monedita.feature.profile.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

data class AccountItem(
    val id: String,
    val name: String,
    val type: String,
    val balance: Double,
    val icon: ImageVector,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    onBackClick: () -> Unit = {}
) {
    val accounts = remember {
        listOf(
            AccountItem("1", "Efectivo", "Billetera", 1250.00, Icons.Default.Payments, MoneditaAvailableGreen),
            AccountItem("2", "BBVA", "Cuenta bancaria", 8450.50, Icons.Default.AccountBalance, MoneditaPrimary),
            AccountItem("3", "Nu", "Tarjeta de débito", 3200.00, Icons.Default.CreditCard, Color(0xFF8B5CF6)),
            AccountItem("4", "Ahorros", "Cuenta de ahorro", 15000.00, Icons.Default.Savings, MoneditaDarkBlue)
        )
    }

    val totalBalance = accounts.sumOf { it.balance }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cuentas",
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
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

            // Total
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MoneditaDarkBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Balance total",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatBalance(totalBalance),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Mis cuentas",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(accounts) { account ->
                    AccountCard(account = account)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = { /* Agregar cuenta */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, MoneditaPrimary),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MoneditaPrimary
                        )
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Agregar cuenta",
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun AccountCard(account: AccountItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, MoneditaBorderGray.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(account.color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = account.icon,
                    contentDescription = null,
                    tint = account.color,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = account.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MoneditaTextStrong
                )
                Text(
                    text = account.type,
                    fontSize = 13.sp,
                    color = MoneditaTextSecondary
                )
            }

            Text(
                text = formatBalance(account.balance),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )
        }
    }

}
private fun formatBalance(balance: Double): String {
    val wholePart = balance.toLong()
    val decimalPart = ((balance - wholePart) * 100).toInt()

    val wholeFormatted = wholePart.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return "$$wholeFormatted.${decimalPart.toString().padStart(2, '0')}"
}
