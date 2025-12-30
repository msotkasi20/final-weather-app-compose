package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.util.weatherVisual
import kotlin.math.roundToInt

@Composable
fun WeatherCard(weather: WeatherResponse, modifier: Modifier = Modifier) {
    val visual = weatherVisual(weather.current.weatherCode, weather.current.isDay)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = visual.icon,
                    contentDescription = visual.description,
                    modifier = Modifier.size(42.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = visual.description,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${weather.current.temperature2m.roundToInt()}°C",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                stringResource(
                    R.string.tuntuu_kuin_c,
                    weather.current.apparentTemperature.roundToInt()
                ))
            Text(stringResource(R.string.tuuli_m_s, weather.current.windSpeed10m.roundToInt()))
        }
    }
}
