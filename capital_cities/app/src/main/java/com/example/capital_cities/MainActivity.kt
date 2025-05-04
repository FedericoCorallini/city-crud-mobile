package com.example.capital_cities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.capital_cities.presentation.navigation.Home
import com.example.capital_cities.presentation.navigation.NavigationGraph
import com.example.capital_cities.presentation.theme.Capital_citiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Capital_citiesTheme {
                NavigationGraph()
            }
        }
    }
}