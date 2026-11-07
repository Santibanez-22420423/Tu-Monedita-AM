package com.example.tu_monedita.feature.profile.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*
import androidx.compose.ui.graphics.Color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var biometricsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Configuración",
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Sección: Preferencias
            SectionTitle("Preferencias")

            SettingsToggleItem(
                icon = Icons.Outlined.Notifications,
                title = "Notificaciones",
                subtitle = "Recibir alertas de gastos y presupuestos",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            SettingsToggleItem(
                icon = Icons.Outlined.DarkMode,
                title = "Modo oscuro",
                subtitle = "Cambiar apariencia de la app",
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it }
            )

            SettingsToggleItem(
                icon = Icons.Outlined.Fingerprint,
                title = "Biometría",
                subtitle = "Usar huella o Face ID",
                checked = biometricsEnabled,
                onCheckedChange = { biometricsEnabled = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección: Datos
            SectionTitle("Datos")

            SettingsClickItem(
                icon = Icons.Outlined.CurrencyExchange,
                title = "Moneda",
                value = "MXN ($)"
            )

            SettingsClickItem(
                icon = Icons.Outlined.Language,
                title = "Idioma",
                value = "Español"
            )

            SettingsClickItem(
                icon = Icons.Outlined.CalendarMonth,
                title = "Inicio de mes",
                value = "Día 1"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección: Seguridad
            SectionTitle("Seguridad")

            SettingsClickItem(
                icon = Icons.Outlined.Lock,
                title = "Cambiar contraseña",
                value = null
            )

            SettingsClickItem(
                icon = Icons.Outlined.Security,
                title = "Autenticación en dos pasos",
                value = "Activada"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección: Datos de la app
            SectionTitle("Aplicación")

            SettingsClickItem(
                icon = Icons.Outlined.Info,
                title = "Versión",
                value = "1.0.0"
            )

            SettingsClickItem(
                icon = Icons.Outlined.DeleteOutline,
                title = "Borrar todos los datos",
                value = null,
                isDestructive = true
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = MoneditaTextSecondary,
        modifier = Modifier.padding(bottom = 8.dp, top = 4.dp)
    )
}

@Composable
private fun SettingsToggleItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MoneditaBorderGray.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MoneditaDarkBlue,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = MoneditaTextStrong
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = MoneditaTextSecondary
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MoneditaPrimary,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = MoneditaBorderGray
                )
            )
        }
    }
}

@Composable
private fun SettingsClickItem(
    icon: ImageVector,
    title: String,
    value: String?,
    isDestructive: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { /* Acción */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MoneditaBorderGray.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDestructive) MoneditaExpenseRed else MoneditaDarkBlue,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDestructive) MoneditaExpenseRed else MoneditaTextStrong,
                modifier = Modifier.weight(1f)
            )

            if (value != null) {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = MoneditaTextSecondary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (!isDestructive) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MoneditaTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
