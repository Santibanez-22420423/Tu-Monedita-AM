package com.example.tu_monedita

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tu_monedita.core.designsystem.theme.TuMoneditaTheme
import com.example.tu_monedita.feature.authentication.presentation.screen.LoginScreen
import com.example.tu_monedita.feature.authentication.presentation.screen.WelcomeScreen
import org.jetbrains.compose.resources.painterResource

import tu_monedita.shared.generated.resources.Res

/**
 * Defines the root composable of Tu Monedita.
 *
 * Applies the application's design system and displays
 * the welcome screen as the temporary entry point.
 *
 * Navigation will be implemented in a later development phase.
 */

@Composable
@Preview
fun App() {
    TuMoneditaTheme {
        // WelcomeScreen()
        LoginScreen()
    }
}
