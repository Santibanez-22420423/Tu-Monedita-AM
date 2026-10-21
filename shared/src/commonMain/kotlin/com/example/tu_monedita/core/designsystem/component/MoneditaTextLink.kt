package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a supporting message followed by an interactive text link.
 *
 * This component is used for secondary actions such as
 * registration and login redirects.
 *
 * @param message Supporting text displayed before the link.
 * @param linkText Text displayed as an interactive link.
 * @param onLinkClick Callback executed when the link is clicked.
 * @param modifier Modifier used to customize the component layout.
 */

@Composable
fun MoneditaTextLink(
    message: String,
    linkText: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        // Display the supporting message.
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.width(LINK_SPACING))

        // Display the interactive link.
        Text(
            text = linkText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable(
                onClick = onLinkClick
            )
        )
    }
}

// Horizontal spacing between the message and the link.
private val LINK_SPACING = 8.dp
