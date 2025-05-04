package com.example.capital_cities.presentation.screens.insert

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.usecases.InsertCapitalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(
    private val insertCapitalUseCase: InsertCapitalUseCase
) : ViewModel() {

    var state by mutableStateOf(InsertState())
        private set

    fun onEventChange(event: InsertEvent) {
        when(event){
            is InsertEvent.ChangeCountry -> onCountryChange(event.country)
            is InsertEvent.ChangeName -> onNameChange(event.name)
            is InsertEvent.ChangePopulation -> onPopulationChange(event.population)
            InsertEvent.Save -> {
                viewModelScope.launch {
                    insertCapitalUseCase(
                        Capital(
                            id = null,
                            name = state.name,
                            country = state.country,
                            population = state.population
                        )
                    )
                }
                state = state.copy(
                    id = null,
                    name = "",
                    country = "",
                    population = 0
                )
            }
        }
    }

    private fun onNameChange(name: String) {
        state = state.copy(name = name)
    }

    private fun onCountryChange(country: String) {
        state = state.copy(country = country)
    }

    private fun onPopulationChange(population: String) {
        if (population.matches(Regex("^\\d{1,10}\$"))){
            state = state.copy(population = population.toLong())
        }
    }
}