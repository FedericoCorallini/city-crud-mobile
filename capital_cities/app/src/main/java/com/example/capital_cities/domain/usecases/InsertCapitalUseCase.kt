package com.example.capital_cities.domain.usecases

import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.repository.CapitalRepository
import javax.inject.Inject

class InsertCapitalUseCase @Inject constructor(
    private val capitalRepository: CapitalRepository
) {
    suspend operator fun invoke(capital: Capital) {
        capitalRepository.insertCapital(capital)
    }
}