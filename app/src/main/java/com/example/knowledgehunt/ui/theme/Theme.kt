package com.example.knowledgehunt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800,
    onBackground = Color.Black,
    onSurface = Color.DarkGray,

    )

private val DarkThemeColors = darkColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.Black,
    secondary = Red700,
    onSecondary = Color.Black,
    error = Red200,
    onBackground = Color.White,
    onSurface = Color.LightGray,
)

@Composable
fun KnowledgeHuntTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )


}