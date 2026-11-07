package com.example.tu_monedita.feature.profile.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.components.MoneditaBottomNavigation
import com.example.tu_monedita.core.components.NavigationOption
import com.example.tu_monedita.core.designsystem.theme.*
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileScreen(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onLogout: () -> Unit
) {
    var currentScreen by remember { mutableStateOf(ProfileScreenState.MAIN) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            MoneditaBottomNavigation(
                selectedItem = selectedItem,
                dynamicOptions = dynamicOptions,
                onItemClick = onItemClick,
                onMoreClick = onMoreClick
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            when (currentScreen) {

                // ===================== PANTALLA PRINCIPAL =====================
                ProfileScreenState.MAIN -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))

                        // Información del usuario
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(110.dp)
                                    .clip(CircleShape)
                                    .background(MoneditaAccent.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "Avatar",
                                    tint = MoneditaAccent,
                                    modifier = Modifier.size(70.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Ana García",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MoneditaTextPrimary
                            )

                            Text(
                                text = "ana@email.com",
                                fontSize = 14.sp,
                                color = MoneditaTextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        // Opciones del perfil
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ProfileOption(
                                icon = Icons.Outlined.Person,
                                title = "Mi perfil",
                                onClick = { currentScreen = ProfileScreenState.MY_PROFILE }
                            )

                            ProfileOption(
                                icon = Icons.Outlined.AccountBalanceWallet,
                                title = "Cuentas",
                                onClick = { currentScreen = ProfileScreenState.ACCOUNTS }
                            )

                            ProfileOption(
                                icon = Icons.Outlined.Settings,
                                title = "Configuración",
                                onClick = { currentScreen = ProfileScreenState.SETTINGS }
                            )

                            ProfileOption(
                                icon = Icons.Outlined.HelpOutline,
                                title = "Ayuda",
                                onClick = { currentScreen = ProfileScreenState.HELP }
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Cerrar sesión
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onLogout() },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MoneditaExpenseBackground
                                ),
                                border = BorderStroke(
                                    1.dp,
                                    MoneditaExpenseRed.copy(alpha = 0.3f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.ExitToApp,
                                        contentDescription = "Cerrar sesión",
                                        tint = MoneditaExpenseRed
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Text(
                                        text = "Cerrar sesión",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MoneditaExpenseRed
                                    )
                                }
                            }
                        }
                    }
                }

                // ===================== CUENTAS =====================
                ProfileScreenState.ACCOUNTS -> {
                    AccountsScreen(
                        onBackClick = { currentScreen = ProfileScreenState.MAIN }
                    )
                }

                // ===================== CONFIGURACIÓN =====================
                ProfileScreenState.SETTINGS -> {
                    SettingsScreen(
                        onBackClick = { currentScreen = ProfileScreenState.MAIN }
                    )
                }

                // ===================== AYUDA =====================
                ProfileScreenState.HELP -> {
                    HelpScreen(
                        onBackClick = { currentScreen = ProfileScreenState.MAIN }
                    )
                }

                // ===================== MI PERFIL (por ahora placeholder) =====================
                ProfileScreenState.MY_PROFILE -> {
                    // Puedes crear después MyProfileScreen
                    // Por ahora regresamos al main
                    MyProfileScreen(
                        onBackClick = { currentScreen = ProfileScreenState.MAIN }
                    )
                }
            }
        }
    }
}


@Composable
fun ProfileOption(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoneditaWhite
        ),
        border = BorderStroke(1.dp, MoneditaBorderGray)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MoneditaDarkBlue
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MoneditaTextPrimary,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MoneditaTextSecondary
            )
        }
    }
}


private enum class ProfileScreenState {
    MAIN,
    MY_PROFILE,
    ACCOUNTS,
    SETTINGS,
    HELP
}
