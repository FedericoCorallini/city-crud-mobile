package com.example.capital_cities.presentation.screens.search

sealed interface SearchEvent {
    data class ChangeCountry(val country: String) : SearchEvent
    data object Search : SearchEvent
}