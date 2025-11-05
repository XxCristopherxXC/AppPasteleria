package com.milsabores.pasteleria.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = ColorMarron,
    secondary = ColorRosa,
    tertiary = Teal200,
    background = ColorFondo,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = ColorTexto,
    onSurface = ColorTexto,
    error = Color(0xFFB00020),
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    tertiary = Purple700,
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Black,
    onSecondary = Black,
    onBackground = White,
    onSurface = White
)

@Composable
fun AppPasteleriaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
