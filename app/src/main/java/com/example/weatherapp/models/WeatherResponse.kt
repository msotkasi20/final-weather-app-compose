package com.example.weatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse (
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeather
)
@Serializable
data class CurrentWeather (
    val time: String,

    // vaihdetaan nimet camelCaseksi
    @SerialName("temperature_2m")
    val temperature2m: Double,

    @SerialName("apparent_temperature")
    val apparentTemperature: Double,

    @SerialName("is_day")
    val isDay: Int,

    @SerialName("weather_code")
    val weatherCode: Int,

    @SerialName("wind_speed_10m")
    val windSpeed10m: Double,

    @SerialName("wind_direction_10m")
    val windDirection10m: Double,

    val rain: Double? = null,
    val showers: Double? = null,
    val snowfall: Double? = null
)