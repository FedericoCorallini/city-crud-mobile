package com.example.capital_cities.presentation.screens.search

sealed interface SearchEvent {
    data class ChangeName(val name: String) : SearchEvent
    data object Search : SearchEvent
}