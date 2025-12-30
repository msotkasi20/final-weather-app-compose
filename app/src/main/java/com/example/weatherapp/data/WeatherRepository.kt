package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.network.WeatherClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherRepository {

    private val client = WeatherClient.client

    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResponse {

        return client.get("https://api.open-meteo.com/v1/forecast") {
            url {
                parameters.append("latitude", lat.toString())
                parameters.append("longitude", lon.toString())
                parameters.append("current", "temperature_2m,apparent_temperature,is_day,weather_code,wind_speed_10m,wind_direction_10m,rain,showers,snowfall")
            }
        }.body()
    }
}