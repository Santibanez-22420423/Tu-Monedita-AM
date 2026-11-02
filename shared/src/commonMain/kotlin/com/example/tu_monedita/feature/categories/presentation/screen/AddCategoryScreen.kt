package com.example.tu_monedita.feature.categories.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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

@Composable
fun AddCategoryScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (String, Boolean) -> Unit = { _, _ -> }
) {
    var name by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        // Encabezado
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = MoneditaTextStrong
                )
            }

            Text(
                text = "Nueva categoría",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MoneditaTextStrong
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Nombre de la categoría",
            fontWeight = FontWeight.SemiBold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej. Mascotas") },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MoneditaPrimary,
                unfocusedBorderColor = MoneditaSearchBackground
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tipo de categoría",
            fontWeight = FontWeight.SemiBold,
            color = MoneditaTextStrong
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(MoneditaSearchBackground)
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
                        .then(
                            Modifier
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (selectedTab == index) Color.White
                        else MoneditaTextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onSaveClick(name.trim(), selectedTab == 1)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = name.isNotBlank(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MoneditaPrimary
            )
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar categoría")
        }
    }
}
