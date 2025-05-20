package com.example.capital_cities.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capital_cities.domain.usecases.DeleteCapitalUseCase
import com.example.capital_cities.domain.usecases.SearchCapitalUseCase
import com.example.capital_cities.domain.usecases.DeleteCapitalsByCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCapitalUseCase: SearchCapitalUseCase,
    private val deleteCapitalUseCase: DeleteCapitalUseCase,
    private val deleteCapitalsByCountryUseCase: DeleteCapitalsByCountryUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ChangeName -> {
                state = state.copy(name = event.name)
            }

            SearchEvent.Search -> {
                viewModelScope.launch {
                    searchCapitalUseCase(state.name).collectLatest { result ->
                        state = state.copy(capitalList = result)
                    }
                }
            }

            is SearchEvent.DeleteCapital -> {
                viewModelScope.launch {
                    deleteCapitalUseCase(event.capital)
                }
            }

            is SearchEvent.ShowDeleteDialog -> {
                state = state.copy(capitalToDelete = event.capital)
            }

            is SearchEvent.HideDeleteDialog -> {
                state = state.copy(capitalToDelete = null)
            }

            is SearchEvent.ConfirmDelete -> {
                viewModelScope.launch {
                    state.capitalToDelete?.let {
                        deleteCapitalUseCase(it)
                        state = state.copy(capitalToDelete = null)
                    }
                }
            }

            SearchEvent.ShowDeleteCountryDialog -> {
                state = state.copy(showDeleteCountryDialog = true)
            }

            SearchEvent.HideDeleteCountryDialog -> {
                state = state.copy(showDeleteCountryDialog = false)
            }

            SearchEvent.ConfirmDeleteCountry -> {
                viewModelScope.launch {
                    val country = state.capitalList.firstOrNull()?.country
                    if (!country.isNullOrBlank()) {
                        deleteCapitalsByCountryUseCase(country)
                        state = state.copy(showDeleteCountryDialog = false)
                        searchCapitalUseCase(state.name).collectLatest {
                            state = state.copy(capitalList = it)
                        }
                    }
                }
            }
        }
    }
}
