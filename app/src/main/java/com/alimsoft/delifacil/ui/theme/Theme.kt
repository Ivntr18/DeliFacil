package com.alimsoft.delifacil.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colorPalette = lightColors(
    primary = Color(0xff8b5000),
    onPrimary = Color.White,
    secondary = Color(0xffaf3000),
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Color(0xff201b16),
    surface = Color.White,
    onSurface = Color(0xff201b16),
    error = Color(0xffba1a1a),
    onError = Color.White
)

@Composable
fun DeliFacilTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}