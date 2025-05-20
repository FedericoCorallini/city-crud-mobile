package com.example.capital_cities.presentation.screens.search

import com.example.capital_cities.domain.model.Capital

sealed interface SearchEvent {
    data class ChangeName(val name: String) : SearchEvent
    data class DeleteCapital(val capital: Capital): SearchEvent
    data object Search : SearchEvent
    data class ShowDeleteDialog(val capital: Capital) : SearchEvent
    object HideDeleteDialog : SearchEvent
    object ConfirmDelete : SearchEvent
    object ShowDeleteCountryDialog : SearchEvent
    object HideDeleteCountryDialog : SearchEvent
    object ConfirmDeleteCountry : SearchEvent
}