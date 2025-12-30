package com.example.weatherapp.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.network.LocationClient
import com.example.weatherapp.ui.components.WeatherCard
import com.example.weatherapp.viewmodels.WeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val vm: WeatherViewModel = viewModel()
    val state by vm.uiState.collectAsState()

    val context = LocalContext.current
    val locationClient = remember { LocationClient(context) }
    val scope = rememberCoroutineScope()

    val gap = 12.dp

    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    var permissionGranted by remember { mutableStateOf(hasLocationPermission()) }
    var askedOnce by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> permissionGranted = isGranted }
    )

    suspend fun refreshWeather() {
        if (!permissionGranted) return
        val loc = locationClient.getCurrentLocation() ?: run {
            vm.showError(context.getString(R.string.sijaintia_ei_saatu_varmista_ett_gps_sijainti_on_p_ll))
            return
        }
        vm.fetchWeather(loc.latitude, loc.longitude)
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted && !askedOnce) {
            askedOnce = true
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Auto-haku ensimmäisellä kerralla KUN lupa on saatu
    LaunchedEffect(permissionGranted) {
        if (permissionGranted && !state.initialFetchStarted) {
            vm.markInitialFetchStarted()
            refreshWeather()
        }
    }

    Column(
        modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {

        if (!permissionGranted) {
            Card {
                Text(
                    text = stringResource(R.string.sijaintilupa_puuttuu_salli_sijainti_jotta_s_voidaan_hakea),
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(Modifier.height(gap))
        }

        when {
            !permissionGranted -> {
                Text(stringResource(R.string.salli_sijainti_ja_paina_p_ivit))
            }

            state.weather == null && state.errorMessage == null -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(12.dp))
                    Text(stringResource(R.string.haetaan_s_tietoa))
                }
            }

            state.errorMessage != null -> Text(state.errorMessage!!)

            else -> WeatherCard(weather = state.weather!!)
        }

            Spacer(Modifier.height(gap))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!permissionGranted) {
                Button(onClick = {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }) {
                    Text(stringResource(R.string.salli_sijainti))
                }
            } else {
                Button(
                    onClick = { scope.launch { refreshWeather() } },
                    enabled = permissionGranted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(stringResource(R.string.p_ivit))
                }
            }
        }
    }
}
