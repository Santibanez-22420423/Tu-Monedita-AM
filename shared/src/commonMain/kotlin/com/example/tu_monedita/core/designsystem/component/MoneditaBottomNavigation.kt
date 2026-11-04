// core/components/MoneditaBottomNavigation.kt
package com.example.tu_monedita.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*

data class NavigationOption(
    val id: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun MoneditaBottomNavigation(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = MoneditaTextSecondary
    ) {
        // 1. INICIO (fijo)
        NavigationBarItem(
            selected = selectedItem == "inicio",
            onClick = { onItemClick("inicio") },
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
            label = { Text("Inicio", fontSize = 11.sp) },
            colors = navigationColors()
        )

        // 2 y 3. Opciones dinámicas (máximo 2)
        dynamicOptions.take(2).forEach { option ->
            NavigationBarItem(
                selected = selectedItem == option.id,
                onClick = { onItemClick(option.id) },
                icon = { Icon(option.icon, contentDescription = option.title) },
                label = { Text(option.title, fontSize = 11.sp) },
                colors = navigationColors()
            )
        }

        // 4. MÁS (fijo)
        NavigationBarItem(
            selected = selectedItem == "mas",
            onClick = { onMoreClick() },
            icon = { Icon(Icons.Outlined.Apps, contentDescription = "Más") },
            label = { Text("Más", fontSize = 11.sp) },
            colors = navigationColors()
        )
    }
}

@Composable
private fun navigationColors() =
    NavigationBarItemDefaults.colors(
        selectedIconColor = MoneditaAccent,
        selectedTextColor = MoneditaAccent,
        indicatorColor = Color.Transparent
    )
