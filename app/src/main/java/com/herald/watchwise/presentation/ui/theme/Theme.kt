package com.herald.watchwise.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ColorPrimary,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = ColorBackground,
    onPrimary = ColorPrimary,
    onSecondary = ColorBackground,

)

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
)

@Composable
fun WatchWiseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = DarkColorPalette

/*        if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}