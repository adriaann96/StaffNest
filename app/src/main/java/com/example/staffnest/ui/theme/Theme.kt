package com.example.staffnest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AzulOscuro,
    background = FondoOscuro,
    surface = FondoOscuro,
    secondary = SeleccionOscuro,
    onPrimary = Color(0xFF001F2A),
    onBackground = TextoOscuro,
    onSurface = TextoOscuro
)

private val LightColorScheme = lightColorScheme(
    primary = AzulClaro,
    background = FondoClaro,
    surface = FondoClaro,
    secondary = SeleccionClaro,
    onPrimary = Color.White,
    onBackground = TextoClaro,
    onSurface = TextoClaro
)

@Composable
fun StaffNestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors =
        if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}