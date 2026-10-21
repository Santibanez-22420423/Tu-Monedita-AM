package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.logo_without_background

/**
 * Displays the Tu Monedita mascot using a shared image resource.
 *
 * The logo size can be customized for different screens
 * while maintaining a consistent visual identity.
 *
 * @param modifier Modifier used to customize the logo layout.
 * @param size Size of the logo.
 * @para contentDescription Accessibility description of the logo.
 */

@Composable
fun MoneditaLogo(
    modifier: Modifier = Modifier,
    size: Dp = DEFAULT_LOGO_SIZE,
    contentDescription: String? = "Tu Monedita logo"
) {
    Image(
        painter = painterResource(Res.drawable.logo_without_background),
        contentDescription = contentDescription,
        modifier = modifier.size((size))
    )
}

// Default size of the application's mascot.
private val DEFAULT_LOGO_SIZE = 180.dp
