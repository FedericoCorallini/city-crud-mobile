package com.example.capital_cities.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capital_cities.domain.usecases.SearchCapitalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCapitalUseCase: SearchCapitalUseCase
) : ViewModel(){
    var state by mutableStateOf(SearchState())

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.ChangeCountry -> onCountryChange(event.country)
            SearchEvent.Search -> {
                viewModelScope.launch {
                    searchCapitalUseCase(state.country).collectLatest {
                        state = state.copy(capitalList = it)
                    }
                }
            }
        }
    }

    private fun onCountryChange(country : String) {
        state = state.copy(country = country)
    }
}