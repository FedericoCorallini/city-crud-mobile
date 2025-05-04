package com.example.capital_cities.presentation.screens.insert

sealed interface InsertEvent {
    data class ChangeName(val name : String) : InsertEvent
    data class ChangeCountry(val country: String) : InsertEvent
    data class ChangePopulation(val population : String) : InsertEvent
    data object Save : InsertEvent
}