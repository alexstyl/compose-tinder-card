package com.alexstyl.swipeablecard.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorPalette = lightColors(
    primary = NiceBlue,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun SwipeableCardTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}