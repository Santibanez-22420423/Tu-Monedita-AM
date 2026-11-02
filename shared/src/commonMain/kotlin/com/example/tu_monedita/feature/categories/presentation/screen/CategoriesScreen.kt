
package com.example.tu_monedita.feature.categories.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.tu_monedita.core.designsystem.theme.*

data class Category(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val color: Color,
    val isIncome: Boolean
)

@Composable
fun main_Categories(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onAddClick: () -> Unit = {},
    onCategoryClick: (Category) -> Unit = {},
    onBottomNavClick: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val categories = remember {
        listOf(
            Category("1", "Comida y bebida", Icons.Default.Restaurant, Color(0xFFE57373), false),
            Category("2", "Transporte", Icons.Default.DirectionsCar, MoneditaTransport, false),
            Category("3", "Vivienda", Icons.Default.Home, MoneditaRent, false),
            Category("4", "Entretenimiento", Icons.Default.SportsEsports, Color(0xFFBA68C8), false),
            Category("5", "Salud", Icons.Default.Favorite, Color(0xFFF06292), false),
            Category("6", "Educación", Icons.Default.School, Color(0xFF4DB6AC), false),
            Category("7", "Ropa", Icons.Default.Checkroom, MoneditaClothing, false),
            Category("8", "Otros", Icons.Default.MoreHoriz, MoneditaTextSecondary, false),
            Category("9", "Salario", Icons.Default.Work, MoneditaAvailableGreen, true),
            Category("10", "Inversiones", Icons.Default.TrendingUp, Color(0xFF4DB6AC), true)
        )
    }

    val currentList = categories.filter {
        it.isIncome == (selectedTab == 1)
    }
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {

            // Encabezado
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

            // Título y botón agregar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Categorías",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoneditaTextStrong
                )

                FloatingActionButton(
                    onClick = onAddClick,
                    modifier = Modifier.size(44.dp),
                    containerColor = MoneditaPrimary,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar categoría"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Selector Gastos e Ingresos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MoneditaSearchBackground),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Gastos", "Ingresos").forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50.dp))
                            .background(
                                if (selectedTab == index) MoneditaPrimary
                                else Color.Transparent
                            )
                            .clickable {
                                selectedTab = index
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color.White
                            else MoneditaTextSecondary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de categorías
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(currentList, key = { it.id }) { category ->
                    CategoryListItem(
                        category = category,
                        onClick = {
                            onCategoryClick(category)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryListItem(
    category: Category,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(category.color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = category.name,
                    tint = category.color,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = category.name,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = "Editar categoría",
                tint = MoneditaTextSecondary
            )
        }
    }
}
