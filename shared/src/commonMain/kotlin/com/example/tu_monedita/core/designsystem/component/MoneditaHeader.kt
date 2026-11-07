package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tu_monedita.core.designsystem.images.MoneditaExpression
import com.example.tu_monedita.core.designsystem.theme.MoneditaTextSecondary

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.monedita_feliz
import tu_monedita.shared.generated.resources.monedita_enojada
import tu_monedita.shared.generated.resources.moneda_triste
import tu_monedita.shared.generated.resources.moneda_guiño
@Composable
fun MoneditaHeader(
    expression: MoneditaExpression = MoneditaExpression.HAPPY,
    modifier: Modifier = Modifier
) {

    val faceImage: DrawableResource = when (expression) {
        MoneditaExpression.HAPPY -> Res.drawable.monedita_feliz
        MoneditaExpression.WINK -> Res.drawable.moneda_guiño
        MoneditaExpression.SAD -> Res.drawable.moneda_triste
        MoneditaExpression.ANGRY -> Res.drawable.monedita_enojada
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(faceImage),
            contentDescription = "Monedita",
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "TU NOMBRE",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MoneditaTextSecondary,
            letterSpacing = 0.5.sp
        )
    }
}
