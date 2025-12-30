package com.example.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.models.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherUiState (
    val weather: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val initialFetchStarted: Boolean = false
)

class WeatherViewModel: ViewModel() {
    private val repository = WeatherRepository()
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun markInitialFetchStarted() {
        if (!_uiState.value.initialFetchStarted) {
            _uiState.value = _uiState.value.copy(initialFetchStarted = true)
        }
    }
    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null, initialFetchStarted = true)
            try {
                val weather = repository.getCurrentWeather(lat,lon)
                _uiState.value = _uiState.value.copy(weather = weather, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun showError(message: String) {
        _uiState.value = _uiState.value.copy(errorMessage = message, isLoading = false, initialFetchStarted = true)
    }
}

