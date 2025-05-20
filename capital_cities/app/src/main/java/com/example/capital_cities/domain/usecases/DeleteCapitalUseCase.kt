package com.example.capital_cities.domain.usecases

import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.repository.CapitalRepository
import javax.inject.Inject

class DeleteCapitalUseCase @Inject constructor(
    private val repository: CapitalRepository
) {
    suspend operator fun invoke(capital: Capital) {
        repository.deleteCapital(capital)
    }
}