
package com.example.tu_monedita.feature.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.tu_monedita.core.designsystem.component.MoneditaPasswordField
import com.example.tu_monedita.core.designsystem.component.MoneditaTextLink

/**
 * Displays the responsive login screen of Tu Monedita.
 *
 * Adapts the layout to different screen sizes and provides
 * vertical scrolling when the available space is limited.
 *
 * Authentication and navigation will be implemented later.
 *
 * @param onLoginClick Callback triggered by the login button.
 * @param onForgotPasswordClick Callback for password recovery.
 * @param onRegisterClick Callback for account registration.
 * @param modifier Modifier used to customize the screen layout.
 */
@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Store temporary input values in the presentation layer.
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .imePadding()
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

        val titleFormSpacing = if (isCompactScreen) {
            COMPACT_TITLE_FORM_SPACING
        } else {
            DEFAULT_TITLE_FORM_SPACING
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .widthIn(max = MAX_FORM_WIDTH)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SCREEN_HORIZONTAL_PADDING)
                .padding(
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
                modifier = Modifier.height(titleFormSpacing)
            )

            // Display the email input section.
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Correo electrónico",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(
                    modifier = Modifier.height(LABEL_FIELD_SPACING)
                )

                MoneditaEmailField(
                    value = email,
                    onValueChange = { email = it }
                )
            }

            Spacer(
                modifier = Modifier.height(INPUT_FIELDS_SPACING)
            )

            // Display the password input field.
            MoneditaPasswordField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña"
            )

            Spacer(
                modifier = Modifier.height(FORGOT_PASSWORD_SPACING)
            )

            // Display the password recovery action.
            Text(
                text = "¿Olvidaste tu contraseña?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(onClick = onForgotPasswordClick)
            )

            Spacer(
                modifier = Modifier.height(FORM_BUTTON_SPACING)
            )

            // Display the primary login action.
            MoneditaButton(
                text = "Iniciar sesión",
                onClick = {
                    onLoginClick(email, password)
                }
            )

            Spacer(
                modifier = Modifier.height(BUTTON_REGISTER_SPACING)
            )

            // Display the registration action.
            MoneditaTextLink(
                message = "¿Aún no tienes una cuenta?",
                linkText = "Regístrate",
                onLinkClick = onRegisterClick
            )
        }
    }
}

// Screen size breakpoint.
private val COMPACT_SCREEN_HEIGHT = 700.dp

// Responsive layout dimensions.
private val MAX_FORM_WIDTH = 440.dp
private val SCREEN_HORIZONTAL_PADDING = 24.dp
private val SCREEN_BOTTOM_PADDING = 24.dp

// Logo dimensions.
private val DEFAULT_LOGO_SIZE = 160.dp
private val COMPACT_LOGO_SIZE = 120.dp

// Vertical positioning.
private val DEFAULT_TOP_PADDING = 72.dp
private val COMPACT_TOP_PADDING = 24.dp

// Component spacing.
private val LOGO_TITLE_SPACING = 4.dp
private val DEFAULT_TITLE_FORM_SPACING = 16.dp
private val COMPACT_TITLE_FORM_SPACING = 12.dp
private val LABEL_FIELD_SPACING = 4.dp
private val INPUT_FIELDS_SPACING = 8.dp
private val FORGOT_PASSWORD_SPACING = 8.dp
private val FORM_BUTTON_SPACING = 36.dp
private val BUTTON_REGISTER_SPACING = 8.dp
