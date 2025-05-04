package com.example.capital_cities.presentation.screens.home

import com.example.capital_cities.domain.model.Capital

data class HomeState(
    val capitalList : List<Capital> = emptyList()
)
