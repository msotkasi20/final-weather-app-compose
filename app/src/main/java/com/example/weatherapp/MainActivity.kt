package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.HomeScreen
import com.example.weatherapp.ui.screens.InfoScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val backStack by navController.currentBackStackEntryAsState()
                val route = backStack?.destination?.route ?: "home"

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = if (route == "info") "Info" else stringResource(R.string.app_name),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            navigationIcon = {
                                if (route == "info") {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(
                                            R.string.takaisin
                                        ))
                                    }
                                }
                            },
                            actions = {
                                if (route == "home") {
                                    IconButton(onClick = { navController.navigate("info") }) {
                                        Icon(Icons.Default.Info, contentDescription = stringResource(
                                            R.string.info
                                        ))
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen() }
                        composable("info") { InfoScreen() }
                    }
                }
            }
        }
    }
}
