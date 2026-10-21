package com.example.tu_monedita.feature.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.tu_monedita.core.designsystem.component.MoneditaButton
import com.example.tu_monedita.core.designsystem.component.MoneditaLogo
import com.example.tu_monedita.core.designsystem.component.MoneditaTextLink
import com.example.tu_monedita.core.designsystem.theme.MoneditaTextPrimary

/**
 * Displays the welcome screen of Tu Monedita.
 *
 * This screen introduces the application and provides
 * access to login and registration actions.
 *
 * Navigations logic will be implemented separately.
 *
 * @param onLogicClick Callback triggered by the login button.
 * @param onRegisterClick Callback triggered by the registration link.
 * @param modifier Modifier used to customize the screen layout.
 */

@Composable
fun WelcomeScreen(
    onLogicClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().background(
            MaterialTheme.colorScheme.background
        ).systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = SCREEN_HORIZONTAL_PADDING
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Display the application mascot.
            MoneditaLogo(
                size = WELCOME_LOGO_SIZE
            )

            Spacer (
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

            Spacer (
                modifier = Modifier.height(TITLE_BUTTON_SPACING)
            )

            // Display the primary login action.
            MoneditaButton(
                text = "Iniciar sesión",
                onClick = onLogicClick
            )

            Spacer(
                modifier = Modifier.height(BUTTON_LINK_SPACING)
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

// Default dimensions for the welcome screen.
private val SCREEN_HORIZONTAL_PADDING = 32.dp
private val WELCOME_LOGO_SIZE = 180.dp
private val LOGO_TITLE_SPACING = 4.dp
private val TITLE_BUTTON_SPACING = 48.dp
private val BUTTON_LINK_SPACING = 8.dp
