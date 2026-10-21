package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays the primary action button used throughout Tu Monedita.
 *
 * The button follows the application's design system and
 * supports customizable text, click actions, and enabled states.
 *
 * @param text Text displayed inside the button.
 * @param onClick Callback executed when the button is clicked.
 * @param modifier Modifier used to customize the button layout.
 * @param enabled Determines whether the button is interactive.
 */

@Composable
fun MoneditaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(BUTTON_HEIGHT),
        enabled = enabled,
        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = BUTTON_ELEVATION
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Default dimensions for the primary button.
private val BUTTON_HEIGHT = 58.dp
private val BUTTON_CORNER_RADIUS = 5.dp
private val BUTTON_ELEVATION = 3.dp
