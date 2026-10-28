package com.example.tu_monedita.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import tu_monedita.shared.generated.resources.Res
import tu_monedita.shared.generated.resources.ic_user

/**
 * Displays a reusable input field for a user's full name.
 *
 * Uses MoneditaTextField to maintain consistent styling
 * across authentication screens.
 *
 * @param value Current full name value.
 * @param onValueChange Callback triggered when the name changes.
 * @param modifier Modifier used to customize the field layout.
 * @param enabled Determines whether the field is editable.
 * @param placeholder Placeholder displayed when the field is empty.
 */

@Composable
fun MoneditaNameField ( // Componente especializado para el nombre completo.
    value: String, // Recibe el nombre escrito por el usuario.
    onValueChange: (String) -> Unit, // Notifica los cambios del texto al componente padre.
    modifier: Modifier = Modifier, // Permite personalizar su disposición.
    enabled: Boolean = true, // Controla si el campo está habilitado.
    placeholder: String = "Nombre completo"
) {
    MoneditaTextField (
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        placeholder = placeholder,
        keyboardType = KeyboardType.Text, // Solicita el teclado de texto estándar.
        leadingIcon = {
            Icon (
                painter = painterResource(Res.drawable.ic_user), // Carga el icono de usuario.
                contentDescription = null,
                modifier = Modifier.size(NAME_ICON_SIZE), // Centraliza el tamaño del icono.
                tint = Color.Unspecified
            )
        }
    )
}

// Default size of the full name input icon.
private val NAME_ICON_SIZE = 22.dp
