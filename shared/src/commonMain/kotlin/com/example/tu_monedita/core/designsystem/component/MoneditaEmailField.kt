package com.example.tu_monedita.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.ic_email

/**
 * Displays a reusable email input field.
 *
 * Uses MoneditaTextField to maintain consistent styling
 * and provides an email-specific keyboard and icon.
 *
 * @param value Current email value.
 * @param onValueChange Callback triggered when the email changes.
 * @param modifier Modifier used to customize the field layout.
 * @param enabled Determines whether the field is editable.
 * @param placeholder Placeholder displayed when the field is empty.
 */

@Composable
fun MoneditaEmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "ejemplo@correo.com"
) {
    MoneditaTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        placeholder = placeholder,
        keyboardType = KeyboardType.Email,
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_email),
                contentDescription = null,
                modifier = Modifier.size(EMAIL_ICON_SIZE),
                tint = Color.Unspecified
            )
        }
    )
}

// Default size of the email input icon.
private val EMAIL_ICON_SIZE = 25.dp
