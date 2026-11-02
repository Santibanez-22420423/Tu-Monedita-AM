// core/navigation/AppNavigation.kt
package com.example.tu_monedita.core.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tu_monedita.core.components.NavigationOption
import com.example.tu_monedita.feature.budgets.presentation.screen.main_Budgets
import com.example.tu_monedita.feature.categories.presentation.screen.main_Categories
import com.example.tu_monedita.feature.dashboard.presentation.screen.HomeScreen
import com.example.tu_monedita.feature.profile.presentation.screen.ProfileScreen
import com.example.tu_monedita.feature.reports.presentation.screen.main_Reports
import com.example.tu_monedita.feature.transactions.presentation.screen.main_Transaction

@Composable
fun AppNavigation() {
    var selectedScreen by remember { mutableStateOf("inicio") }

    // Las 2 opciones dinámicas que se muestran en el medio
    // Inicialmente: Movimientos + Presupuestos
    var dynamicOptions by remember {
        mutableStateOf(
            listOf(
                availableNavigationOptions[0], // movimientos
                availableNavigationOptions[1]  // presupuestos
            )
        )
    }

    // Cuando el usuario elige una opción desde "Más"
    fun selectFromMore(optionId: String) {

        // Si selecciona Inicio, regresar directamente
        if (optionId == "inicio") {
            selectedScreen = "inicio"
            return
        }

        val option = availableNavigationOptions.find {
            it.id == optionId
        } ?: return

        // Mover la opción seleccionada al primer espacio
        val withoutThis = dynamicOptions.filter {
            it.id != optionId
        }

        dynamicOptions = listOf(option) + withoutThis.take(1)

        selectedScreen = optionId
    }

    // Navegación desde la barra inferior (Inicio + dinámicos)
    fun onBottomItemClick(id: String) {
        selectedScreen = id
    }

    when (selectedScreen) {
        "inicio" -> {
            HomeScreen(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" },
                onNavigateToNewTransaction = { selectedScreen = "movimientos" }
            )
        }

        "movimientos" -> {
            main_Transaction(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" }
            )
        }

        "presupuestos" -> {
            main_Budgets(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" }
            )
        }

        "categorias" -> {
            main_Categories(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" }
            )
        }

        "reportes" -> {
            main_Reports(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" }
            )
        }

        "perfil" -> {
            ProfileScreen(
                selectedItem = selectedScreen,
                dynamicOptions = dynamicOptions,
                onItemClick = ::onBottomItemClick,
                onMoreClick = { selectedScreen = "mas" },
                onLogout = { selectedScreen = "inicio" }
            )
        }

        "mas" -> {
            MoreScreen(
                onNavigate = { screenId ->
                    selectFromMore(screenId)
                }
            )
        }
    }
}


@Composable
fun MoreScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Más opciones")

        Spacer(modifier = Modifier.height(24.dp))

        availableNavigationOptions.forEach { option ->
            Button(
                onClick = {
                    onNavigate(option.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(option.title)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onNavigate("inicio")
            }
        ) {
            Text("Regresar a Inicio")
        }
    }
}

