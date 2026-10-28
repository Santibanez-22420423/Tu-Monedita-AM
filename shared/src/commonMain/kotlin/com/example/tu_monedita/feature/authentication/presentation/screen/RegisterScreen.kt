package com.example.tu_monedita.feature.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.tu_monedita.core.designsystem.component.MoneditaButton
import com.example.tu_monedita.core.designsystem.component.MoneditaEmailField
import com.example.tu_monedita.core.designsystem.component.MoneditaLogo
import com.example.tu_monedita.core.designsystem.component.MoneditaNameField
import com.example.tu_monedita.core.designsystem.component.MoneditaPasswordField
import com.example.tu_monedita.core.designsystem.component.MoneditaSecurityNotice
import com.example.tu_monedita.core.designsystem.component.MoneditaTextLink

/**
 * Displays the responsive registration screen of Tu Monedita.
 *
 * Allows users to enter their full name, email, password,
 * and password confirmation.
 *
 * Registration logic and navigation will be implemented later.
 *
 * @param onRegisterClick Callback triggered by the register button.
 * @param onLoginClick Callback triggered by the login link.
 * @param modifier Modifier used to customize the screen layout.
 */

@Composable
fun RegisterScreen(
    onRegisterClick: (String, String, String, String) -> Unit = {
            _, _, _, _->
    },
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Store temporary registration form values.
    var fullName by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize().background(
            MaterialTheme.colorScheme.background
        ).systemBarsPadding().imePadding()
    ) {
        // Adjust layout dimensions based on available height.
        val isCompactScreen = maxHeight < COMPACT_SCREEN_HEIGHT
        val logoSize = if (isCompactScreen) {
            COMPACT_LOGO_SIZE
        } else {
            DEFAULT_LOGO_SIZE
        }
        val topPadding = if (isCompactScreen) {
            COMPACT_TOP_PADDING
        } else {
            DEFAULT_TOP_PADDING
        }
        val sectionSpacing = if (isCompactScreen) {
            COMPACT_SECTION_SPACING
        } else {
            DEFAULT_SECTION_SPACING
        }

        Column(
            modifier = Modifier.align(
                Alignment.TopCenter
            ).widthIn(
                max = MAX_FORM_WIDTH
            ).fillMaxWidth().verticalScroll(
                rememberScrollState()
            ).padding(
                horizontal = SCREEN_HORIZONTAL_PADDING
            ).padding(
                top = topPadding,
                bottom = SCREEN_BOTTOM_PADDING
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the application mascot.
            MoneditaLogo(
                size = logoSize
            )

            Spacer(
                modifier = Modifier.height(LOGO_TITLE_SPACING)
            )

            // Display the application name.
            Text(
                text = "Tu monedita",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(TITLE_SUBTITLE_SPACING)
            )

            // Display the registration heading.
            Text(
                text = "Crea tu cuenta",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(SUBTITLE_DESCRIPTION_SPACING)
            )

            Text(
                text = "Comienza a organizar tus finanzas de forma fácil y segura",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = DESCRIPTION_ALPHA
                ),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(sectionSpacing)
            )

            // Display the full name field.
            MoneditaNameField(
                value = fullName,
                onValueChange = { fullName = it }
            )

            Spacer(
                modifier = Modifier.height(INPUT_FIELDS_SPACING)
            )

            // Display the email field.
            MoneditaEmailField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Correo electrónico"
            )

            Spacer(
                modifier = Modifier.height(INPUT_FIELDS_SPACING)
            )

            // Display the password field.
            MoneditaPasswordField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña"
            )

            Spacer(
                modifier = Modifier.height(INPUT_FIELDS_SPACING)
            )

            // Display the password confirmation field.
            MoneditaPasswordField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirmar contraseña"
            )

            Spacer(
                modifier = Modifier.height(sectionSpacing)
            )

            // Display the security information notice.
            MoneditaSecurityNotice(
                title = "Tu información está segura",
                description = "Utilizamos medidas de seguridad para proteger tus datos"
            )

            Spacer(
                modifier = Modifier.height(sectionSpacing)
            )

            // Display the primary registration action.
            MoneditaButton(
                text = "Crear cuenta",
                onClick = {
                    onRegisterClick(
                        fullName,
                        email,
                        password,
                        confirmPassword
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(BUTTON_LINK_SPACING)
            )

            // Display the login action.
            MoneditaTextLink(
                message = "¿Ya tienes una cuenta?",
                linkText = "Iniciar sesión",
                onLinkClick = onLoginClick
            )
        }
    }
}

// Screen size breakpoint
private val COMPACT_SCREEN_HEIGHT = 700.dp

// Responsive layout dimensions.
private val MAX_FORM_WIDTH = 440.dp
private val SCREEN_HORIZONTAL_PADDING = 24.dp
private val SCREEN_BOTTOM_PADDING = 24.dp

// Logo dimensions
private val DEFAULT_LOGO_SIZE = 140.dp
private val COMPACT_LOGO_SIZE = 105.dp

// Vertical positioning.
private val DEFAULT_TOP_PADDING = 24.dp
private val COMPACT_TOP_PADDING = 12.dp

// Component spacing
private val LOGO_TITLE_SPACING = 4.dp
private val TITLE_SUBTITLE_SPACING = 8.dp
private val SUBTITLE_DESCRIPTION_SPACING = 4.dp
private val DEFAULT_SECTION_SPACING = 24.dp
private val COMPACT_SECTION_SPACING = 16.dp
private val INPUT_FIELDS_SPACING = 8.dp
private val BUTTON_LINK_SPACING = 8.dp

// Supporting text opacity.
private const val DESCRIPTION_ALPHA = 0.6f
