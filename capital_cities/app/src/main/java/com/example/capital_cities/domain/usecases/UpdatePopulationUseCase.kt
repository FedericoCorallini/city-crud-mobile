package com.example.capital_cities.domain.usecases

import com.example.capital_cities.domain.repository.CapitalRepository
import javax.inject.Inject

class UpdatePopulationUseCase @Inject constructor(
    private val repository: CapitalRepository
) {
    suspend operator fun invoke(id: Int, newPopulation: Long) {
        repository.updatePopulation(id, newPopulation)
    }
}
