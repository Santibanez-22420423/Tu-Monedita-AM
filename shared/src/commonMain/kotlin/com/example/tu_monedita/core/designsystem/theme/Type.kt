
package com.example.tu_monedita.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.poppins_regular
import tu_monedita.shared.generated.resources.poppins_medium
import tu_monedita.shared.generated.resources.poppins_semibold

/**
 * Creates the Poppins font family using shared font resources.
 *
 * Each font resource is associated with its corresponding weight.
 */
@Composable
fun PoppinsFontFamily(): FontFamily {
    val regularFont = Font(
        resource = Res.font.poppins_regular,
        weight = FontWeight.Normal
    )

    val mediumFont = Font(
        resource = Res.font.poppins_medium,
        weight = FontWeight.Medium
    )

    val semiBoldFont = Font(
        resource = Res.font.poppins_semibold,
        weight = FontWeight.SemiBold
    )

    return remember(regularFont, mediumFont, semiBoldFont) {
        FontFamily(
            regularFont,
            mediumFont,
            semiBoldFont
        )
    }
}

/**
 * Creates the typography styles used throughout Tu Monedita.
 *
 * These styles ensure consistent font sizes, weights,
 * and line heights across application screens.
 */
@Composable
fun rememberTuMoneditaTypography(): Typography {
    val fontFamily = PoppinsFontFamily()

    return remember(fontFamily) {
        Typography(
            headlineLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                lineHeight = 44.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 36.sp
            ),
            titleLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                lineHeight = 28.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            bodyMedium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            bodySmall = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp
            ),
            labelLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            labelMedium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        )
    }
}
