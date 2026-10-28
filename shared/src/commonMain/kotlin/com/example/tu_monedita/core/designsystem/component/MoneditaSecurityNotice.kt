package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.ic_shield_check_gray

/**
 * Displays a reusable informational notice with a security icon.
 *
 * This component presents a title and supporting message
 * without implementing security or authentication logic.
 *
 * @param title Main message displayed in the notice.
 * @param description Supporting information displayed below the title.
 * @param modifier Modifier used to customize the layout.
 */

@Composable
fun MoneditaSecurityNotice ( // Componente informativo reutilizable.
    title: String, // Título principal del aviso.
    description: String, // Mensaje secundario.
    modifier: Modifier = Modifier // Permite personalizar su distribución.
) {
    Row ( // Coloca el ícono junto a los textos.
        modifier = modifier.fillMaxWidth().background(
            color = NOTICE_BACKGROUND_COLOR,
            shape = RoundedCornerShape(NOTICE_CORNER_RADIUS) // Define las esquinas redondeadas.
        ).padding(NOTICE_PADDING),
        horizontalArrangement = Arrangement.spacedBy(
            ICON_TEXT_SPACING
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display the decorative security icon.
        Icon (
            painter = painterResource(
                Res.drawable.ic_shield_check_gray // Carga el icono de escudo.
            ),
            contentDescription = null,
            modifier = Modifier.size(SECURITY_ICON_SIZE),
            tint = Color.Unspecified
        )

        // Display the notice title and supporting description.
        Column( // Organiza el título y la descripción verticalmente.
            verticalArrangement = Arrangement.spacedBy(
                TEXT_VERTICAL_SPACING
            )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = NOTICE_DESCRIPTION_COLOR
            )
        }
    }
}

// Default dimensions for the informational notice.
private val NOTICE_PADDING = 12.dp
private val NOTICE_CORNER_RADIUS = 12.dp
private val SECURITY_ICON_SIZE = 32.dp
private val ICON_TEXT_SPACING = 12.dp
private val TEXT_VERTICAL_SPACING = 2.dp

// Notice colors.
private val NOTICE_BACKGROUND_COLOR = Color(0xFFF1EEEE)
private val NOTICE_DESCRIPTION_COLOR = Color(0xFF858585)
