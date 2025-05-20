package com.example.capital_cities.presentation.screens.search

import com.example.capital_cities.domain.model.Capital

data class SearchState(
    val name: String = "",
    val capitalList : List<Capital> = emptyList(),
    val capitalToDelete: Capital? = null,
    val showDeleteCountryDialog: Boolean = false
)
