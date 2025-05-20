package com.example.capital_cities.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.usecases.SearchCapitalUseCase
import com.example.capital_cities.domain.usecases.UpdatePopulationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchCapitalUseCase: SearchCapitalUseCase,
    private val updatePopulationUseCase: UpdatePopulationUseCase
) : ViewModel() {

    var state = HomeState()
        private set

    init {
        loadAllCapitals()
    }

    fun loadAllCapitals() {
        viewModelScope.launch {
            searchCapitalUseCase("").collectLatest {
                state.capitalList = it
            }
        }
    }

    fun showEditDialog(capital: Capital) {
        state.capitalToEdit = capital
        state.newPopulation = capital.population.toString()
        state.showEditDialog = true
    }

    fun hideEditDialog() {
        state.showEditDialog = false
    }

    fun confirmEdit() {
        viewModelScope.launch {
            val id = state.capitalToEdit?.id ?: return@launch
            val population = state.newPopulation.toLongOrNull() ?: return@launch

            updatePopulationUseCase(id, population)
            state.showEditDialog = false
            loadAllCapitals()
        }
    }
}
