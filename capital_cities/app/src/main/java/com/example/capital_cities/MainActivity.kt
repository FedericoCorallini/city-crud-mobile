package com.example.capital_cities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.capital_cities.presentation.navigation.NavigationGraph
import com.example.capital_cities.presentation.theme.CapitalCitiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapitalCitiesTheme {
                NavigationGraph()
            }
        }
    }
}