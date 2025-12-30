package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PinkPrimary,
    onPrimary = OnPrimary,
    secondary = PinkSecondary,
    onSecondary = OnSecondary,
    background = AppBackground,
    onBackground = OnBackground,
    surface = AppSurface,
    onSurface = OnSurface,
    error = Error,
    onError = OnError
)

private val LightColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = OnPrimary,
    secondary = PinkSecondary,
    onSecondary = OnSecondary,
    background = AppBackground,
    onBackground = OnBackground,
    surface = AppSurface,
    onSurface = OnSurface,
    error = Error,
    onError = OnError
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
