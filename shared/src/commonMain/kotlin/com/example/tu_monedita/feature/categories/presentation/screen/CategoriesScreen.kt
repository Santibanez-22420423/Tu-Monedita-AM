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
import com.example.tu_monedita.core.designsystem.component.MoneditaHeader
import com.example.tu_monedita.core.designsystem.images.MoneditaExpression
import com.example.tu_monedita.core.designsystem.theme.*


data class Category(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val color: Color,
    val isIncome: Boolean
)


@OptIn(ExperimentalMaterial3Api::class)
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

    // ===================== ESTADOS DE NAVEGACIÓN =====================
    var currentScreen by remember { mutableStateOf(CategoryScreen.MAIN) }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var selectedTab by remember { mutableIntStateOf(0) }

    val categories = remember {
        listOf(
            Category("1", "Comida y bebida", Icons.Default.Restaurant, MoneditaFood, false),
            Category("2", "Transporte", Icons.Default.DirectionsCar, MoneditaTransport, false),
            Category("3", "Vivienda", Icons.Default.Home, MoneditaRent, false),
            Category("4", "Entretenimiento", Icons.Default.SportsEsports, MoneditaEntretaiment, false),
            Category("5", "Salud", Icons.Default.Favorite, MoneditaFavorite, false),
            Category("6", "Educación", Icons.Default.School, MoneditaEducation, false),
            Category("7", "Ropa", Icons.Default.Checkroom, MoneditaClothing, false),
            Category("8", "Otros", Icons.Default.MoreHoriz, MoneditaTextSecondary, false),
            Category("9", "Salario", Icons.Default.Work, MoneditaAvailableGreen, true),
            Category("10", "Inversiones", Icons.Default.TrendingUp, MoneditaInvestments, true)
        )
    }

    val currentList = categories.filter {
        it.isIncome == (selectedTab == 1)
    }

    // ===================== SCAFFOLD COMPARTIDO =====================
    Scaffold(
        containerColor = Color.White,

        bottomBar = {
            MoneditaBottomNavigation(
                selectedItem = selectedItem,
                dynamicOptions = dynamicOptions,
                onItemClick = onItemClick,
                onMoreClick = onMoreClick
            )
        },

        floatingActionButton = {
            if (currentScreen == CategoryScreen.MAIN) {
                FloatingActionButton(
                    onClick = {
                        currentScreen = CategoryScreen.CREATE
                    },
                    containerColor = MoneditaAccent,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar categoría"
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
                CategoryScreen.MAIN -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {

                        // Encabezado
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoneditaHeader(expression = MoneditaExpression.WINK)

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
                            text = "Categorías",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoneditaTextStrong,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )

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
                                            if (selectedTab == index)
                                                if (title == "Gastos") MoneditaExpenseRed
                                                else MoneditaAvailableGreen
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
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
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
                                        selectedCategory = category
                                        currentScreen = CategoryScreen.EDIT
                                    }
                                )
                            }
                        }
                    }
                }

                // ===================== CREAR CATEGORÍA =====================
                CategoryScreen.CREATE -> {
                    AddCategoryScreen(
                        onBackClick = {
                            currentScreen = CategoryScreen.MAIN
                        },
                        onSaveClick = { name, isIncome ->
                            // Aquí posteriormente guardas la nueva categoría
                            currentScreen = CategoryScreen.MAIN
                        }
                    )
                }

                // ===================== EDITAR CATEGORÍA =====================
                CategoryScreen.EDIT -> {
                    selectedCategory?.let { category ->
                        EditCategoryScreen(
                            category = category,
                            onBackClick = {
                                currentScreen = CategoryScreen.MAIN
                            },
                            onSaveClick = { name, isIncome ->
                                // Aquí posteriormente actualizas la categoría
                                currentScreen = CategoryScreen.MAIN
                            },
                            onDeleteClick = {
                                // Aquí posteriormente eliminas la categoría
                                currentScreen = CategoryScreen.MAIN
                                selectedCategory = null
                            }
                        )
                    }
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


private enum class CategoryScreen {
    MAIN,
    CREATE,
    EDIT
}
