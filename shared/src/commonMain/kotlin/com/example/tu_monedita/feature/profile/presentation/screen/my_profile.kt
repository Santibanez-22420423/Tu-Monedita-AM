package com.example.tu_monedita.feature.profile.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (name: String, email: String, phone: String) -> Unit = { _, _, _ -> }
) {
    var name by remember { mutableStateOf("Ana García") }
    var email by remember { mutableStateOf("ana@email.com") }
    var phone by remember { mutableStateOf("55 1234 5678") }
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi perfil",
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
                actions = {
                    TextButton(
                        onClick = {
                            if (isEditing) {
                                onSaveClick(name, email, phone)
                            }
                            isEditing = !isEditing
                        }
                    ) {
                        Text(
                            text = if (isEditing) "Guardar" else "Editar",
                            color = MoneditaPrimary,
                            fontWeight = FontWeight.SemiBold
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
            Spacer(modifier = Modifier.height(24.dp))

            // Avatar
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
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

                    // Botón cambiar foto (solo visible en modo edición)
                    if (isEditing) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(MoneditaPrimary)
                                .clickable { /* Abrir selector de imagen */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Cambiar foto",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Campos
            ProfileField(
                label = "Nombre completo",
                value = name,
                enabled = isEditing,
                onValueChange = { name = it },
                icon = Icons.Outlined.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(
                label = "Correo electrónico",
                value = email,
                enabled = isEditing,
                onValueChange = { email = it },
                icon = Icons.Outlined.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(
                label = "Teléfono",
                value = phone,
                enabled = isEditing,
                onValueChange = { phone = it },
                icon = Icons.Outlined.Phone
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Información adicional (solo lectura)
            Text(
                text = "Información de la cuenta",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MoneditaTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "Fecha de registro",
                value = "15 de marzo de 2024"
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoCard(
                title = "Plan actual",
                value = "Gratuito"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón eliminar cuenta (solo en modo edición)
            if (isEditing) {
                OutlinedButton(
                    onClick = { /* Confirmar eliminación */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MoneditaExpenseRed
                    ),
                    border = BorderStroke(1.dp, MoneditaExpenseRed.copy(alpha = 0.5f))
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Eliminar cuenta",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}


@Composable
private fun ProfileField(
    label: String,
    value: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = MoneditaTextSecondary
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (enabled) MoneditaPrimary else MoneditaTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MoneditaPrimary,
                unfocusedBorderColor = MoneditaBorderGray,
                disabledBorderColor = MoneditaBorderGray.copy(alpha = 0.5f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = MoneditaSearchBackground.copy(alpha = 0.4f),
                disabledTextColor = MoneditaTextStrong
            )
        )
    }
}


@Composable
private fun InfoCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MoneditaBorderGray.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = MoneditaTextSecondary
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MoneditaTextStrong
            )
        }
    }
}
