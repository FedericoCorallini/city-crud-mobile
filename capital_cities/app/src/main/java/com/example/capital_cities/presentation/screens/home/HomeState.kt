package com.example.capital_cities.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.capital_cities.domain.model.Capital

class HomeState {
    var capitalList by mutableStateOf<List<Capital>>(emptyList())
    var capitalToEdit by mutableStateOf<Capital?>(null)
    var newPopulation by mutableStateOf("")
    var showEditDialog by mutableStateOf(false)
}

