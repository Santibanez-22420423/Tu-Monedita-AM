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
fun HelpScreen(
    onBackClick: () -> Unit = {}
) {
    val faqs = listOf(
        "¿Cómo creo un presupuesto?" to "Ve a la sección de Presupuestos y toca el botón + para crear uno nuevo.",
        "¿Cómo agrego un movimiento?" to "En la pantalla de Movimientos toca el botón + y completa los datos del ingreso o gasto.",
        "¿Puedo editar una categoría?" to "Sí. En Categorías selecciona la que quieras modificar y edita su nombre o tipo.",
        "¿Cómo cambio mi moneda?" to "Ve a Configuración → Moneda y selecciona la que desees usar.",
        "¿Mis datos están seguros?" to "Sí. Tu información se almacena de forma segura y puedes activar biometría para mayor protección."
    )

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ayuda",
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

            // Contacto rápido
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
                        text = "¿Necesitas ayuda?",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Estamos aquí para apoyarte",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ContactChip(
                            icon = Icons.Outlined.Email,
                            text = "Email",
                            modifier = Modifier.weight(1f)
                        )
                        ContactChip(
                            icon = Icons.Outlined.Chat,
                            text = "Chat",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Preguntas frecuentes",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )

            Spacer(modifier = Modifier.height(12.dp))

            faqs.forEach { (question, answer) ->
                FaqItem(question = question, answer = answer)
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Más opciones
            Text(
                text = "Más opciones",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextStrong
            )

            Spacer(modifier = Modifier.height(12.dp))

            HelpOptionItem(
                icon = Icons.Outlined.MenuBook,
                title = "Guía de uso",
                subtitle = "Aprende a usar todas las funciones"
            )

            HelpOptionItem(
                icon = Icons.Outlined.BugReport,
                title = "Reportar un problema",
                subtitle = "Cuéntanos qué salió mal"
            )

            HelpOptionItem(
                icon = Icons.Outlined.StarOutline,
                title = "Calificar la app",
                subtitle = "Tu opinión nos ayuda a mejorar"
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ContactChip(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(44.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        color = Color.White.copy(alpha = 0.15f)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun FaqItem(
    question: String,
    answer: String
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MoneditaBorderGray.copy(alpha = 0.6f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = MoneditaTextStrong,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = MoneditaTextSecondary
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = answer,
                    fontSize = 14.sp,
                    color = MoneditaTextSecondary,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun HelpOptionItem(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { },
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
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MoneditaTextSecondary
            )
        }
    }
}
