package com.example.tu_monedita.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Defines the light color scheme for Tu Monedita.
 *
 * The color scheme uses the application's shared
 * color palette to maintain visual consistency.
 */

private val LightColorScheme = lightColorScheme(
    primary = MoneditaPrimary,
    onPrimary = MoneditaWhite,

    secondary = MoneditaAccent,

    background = MoneditaBackground,
    onBackground = MoneditaTextPrimary,

    surface = MoneditaBackground,
    onSurface = MoneditaTextPrimary
)

/**
 * Applies the global design system to the application.
 *
 * This theme provides consistent colors and typography
 * for all screens and reusable UI components.
 *
 * @param content The composable content that uses this theme.
 */

@Composable
fun TuMoneditaTheme(
    content: @Composable () -> Unit
) {
    val typography = rememberTuMoneditaTypography()

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = typography,
        content = content
    )
}
