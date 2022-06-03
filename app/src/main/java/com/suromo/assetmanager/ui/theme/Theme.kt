package com.suromo.assetmanager.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LightThemeColors = lightColors(
    primary = Yellow700,
    primaryVariant = Yellow900,
    onPrimary = Color.White,
    secondary = Yellow700,
    secondaryVariant = Yellow900,
    onSecondary = Color.White,
    error = Yellow800,
    onBackground = Color.Black,

    )

private val DarkThemeColors = darkColors(
    primary = Yellow300,
    primaryVariant = Yellow700,
    onPrimary = Color.Black,
    secondary = Yellow300,
    onSecondary = Color.Black,
    error = Yellow200,
    onBackground = Color.White
)

@Composable
fun AssetManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    androidx.compose.material.MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}