// core/navigation/NavigationOptions.kt
package com.example.tu_monedita.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.tu_monedita.core.components.NavigationOption

val availableNavigationOptions = listOf(
    NavigationOption("movimientos", "Movimientos", Icons.Default.ReceiptLong),
    NavigationOption("presupuestos", "Presupuestos", Icons.Default.AccountBalanceWallet),
    NavigationOption("reportes", "Reportes", Icons.Default.BarChart),
    NavigationOption("perfil", "Perfil", Icons.Default.Person),
    NavigationOption("categorias", "Categorías", Icons.Default.Category)
)
