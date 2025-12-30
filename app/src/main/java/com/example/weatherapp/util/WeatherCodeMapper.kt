package com.example.weatherapp.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

data class WeatherVisual(
    val icon: ImageVector,
    val description: String
)

fun weatherVisual(code: Int, isDay: Int): WeatherVisual {
    val isD = isDay == 1

    return when (code) {
        0 -> WeatherVisual(
            icon = if (isD) Icons.Rounded.WbSunny else Icons.Rounded.NightsStay,
            description = "Selkeää"
        )

        1 -> WeatherVisual(Icons.Rounded.CloudQueue, "Melkein selkeää")
        2 -> WeatherVisual(Icons.Rounded.CloudQueue, "Puolipilvistä")
        3 -> WeatherVisual(Icons.Rounded.Cloud, "Pilvistä")

        45, 48 -> WeatherVisual(Icons.Rounded.Cloud, "Sumuista")

        in 51..57 -> WeatherVisual(Icons.Rounded.Grain, "Tihkusadetta")
        in 61..67 -> WeatherVisual(Icons.Rounded.WaterDrop, "Vesisadetta")
        in 71..77 -> WeatherVisual(Icons.Rounded.AcUnit, "Lumisadetta")
        in 80..82 -> WeatherVisual(Icons.Rounded.Umbrella, "Sadekuuroja")
        in 95..99 -> WeatherVisual(Icons.Rounded.Thunderstorm, "Ukkosta")

        else -> WeatherVisual(Icons.Rounded.Cloud, "Vaihtelevasti")
    }
}
