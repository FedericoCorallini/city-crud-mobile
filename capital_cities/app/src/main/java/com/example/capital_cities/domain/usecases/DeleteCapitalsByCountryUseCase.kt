package com.example.capital_cities.domain.usecases

import com.example.capital_cities.domain.repository.CapitalRepository
import javax.inject.Inject

class DeleteCapitalsByCountryUseCase @Inject constructor(
    private val repository: CapitalRepository
) {
    suspend operator fun invoke(country: String) {
        repository.deleteAllByCountry(country)
    }
}
