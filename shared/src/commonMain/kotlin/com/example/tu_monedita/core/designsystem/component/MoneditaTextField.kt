package com.example.tu_monedita.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Displays a reusable text input field.
 *
 * This component provides consistent styling
 * for input fields throughout Tu Monedita.
 *
 * @param value Current text value.
 * @param onValueChange Callback triggered when the text changes.
 * @param placeholder Placeholder displayed when the field is empty.
 * @param modifier Modifier used to customize the field layout.
 * @param enabled Determines whether the field is editable.
 * @param keyboardType Defines the keyboard input type.
 * @param visualTransformation Defines how the text is displayed.
 * @param leadingIcon Optional icon displayed before the input.
 * @param trailingIcon Optional icon displayed after the input.
 */

@Composable
fun MoneditaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth().height(TEXT_FIELD_HEIGHT),
        enabled = enabled,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

// Default dimensions for the input field.
private val TEXT_FIELD_HEIGHT = 56.dp
private val TEXT_FIELD_CORNER_RADIUS = 5.dp
