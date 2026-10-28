package com.example.tu_monedita.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.ic_lock
import tu_monedita.shared.generated.resources.ic_password_visibility_off
import tu_monedita.shared.generated.resources.ic_password_visibility_on

/**
 * Displays a reusable password input field.
 *
 * Uses MoneditaTextField to maintain consistent styling
 * and provides a button to toggle password visibility.
 *
 * @param value Current password value.
 * @param onValueChange Callback triggered when the password changes.
 * @param modifier Modifier used to customize the field layout.
 * @param enabled Determines whether the field is editable.
 * @param placeholder Placeholder displayed when the field is empty.
 */

@Composable
fun MoneditaPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Contraseña"
) {
    // Preserve the password visibility state across recompositions.
    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val visualTransformation = if(isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    MoneditaTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        placeholder = placeholder,
        keyboardType = KeyboardType.Password,
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_lock),
                contentDescription = null,
                modifier = Modifier.size(LOCK_ICON_SIZE),
                tint = Color.Unspecified
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                },
                enabled = enabled
            ) {
                Icon(
                    painter = painterResource(
                        if (isPasswordVisible) {
                            Res.drawable.ic_password_visibility_off
                        } else {
                            Res.drawable.ic_password_visibility_on
                        }
                    ),
                    contentDescription = if(isPasswordVisible) {
                        "Ocultar contraseña"
                    } else {
                        "Mostrar contraseña"
                    },
                    modifier = Modifier.size(VISIBILITY_ICON_SIZE),
                    tint = Color.Unspecified
                )
            }
        }
    )
}

// Default dimensions for password field icons.
private val LOCK_ICON_SIZE = 25.dp
private val VISIBILITY_ICON_SIZE = 25.dp
