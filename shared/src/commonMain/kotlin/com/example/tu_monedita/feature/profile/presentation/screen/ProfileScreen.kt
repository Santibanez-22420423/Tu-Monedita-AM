
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
import androidx.compose.runtime.Composable
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

@Composable
fun ProfileScreen(
    selectedItem: String,
    dynamicOptions: List<NavigationOption>,
    onItemClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    onLogout: () -> Unit
) {
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MoneditaBackground)
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
                        title = "Mi perfil"
                    )

                    ProfileOption(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        title = "Cuentas"
                    )

                    ProfileOption(
                        icon = Icons.Outlined.Settings,
                        title = "Configuración"
                    )

                    ProfileOption(
                        icon = Icons.Outlined.HelpOutline,
                        title = "Ayuda"
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
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    title: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Lógica futura */ },
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
