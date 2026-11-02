package com.example.tu_monedita.feature.categories.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tu_monedita.core.designsystem.theme.*

@Composable
fun EditCategoryScreen(
    category: Category,
    onBackClick: () -> Unit = {},
    onSaveClick: (String, Boolean) -> Unit = { _, _ -> },
    onDeleteClick: () -> Unit = {}
) {
    var name by remember(category.id) {
        mutableStateOf(category.name)
    }

    var selectedTab by remember(category.id) {
        mutableIntStateOf(if (category.isIncome) 1 else 0)
    }

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
                text = "Editar categoría",
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
                .background(
                    MoneditaSearchBackground,
                    RoundedCornerShape(50.dp)
                )
        ) {
            listOf("Gastos", "Ingresos").forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            if (selectedTab == index) MoneditaPrimary
                            else Color.Transparent,
                            RoundedCornerShape(50.dp)
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
            Text("Guardar cambios")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MoneditaExpenseRed
            )
        ) {
            Text("Eliminar categoría")
        }
    }
}
