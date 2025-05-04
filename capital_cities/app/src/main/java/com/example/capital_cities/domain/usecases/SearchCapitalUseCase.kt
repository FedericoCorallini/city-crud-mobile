package com.example.capital_cities.domain.usecases

import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.repository.CapitalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCapitalUseCase @Inject constructor(
    private val capitalRepository: CapitalRepository
) {
    operator fun invoke(country : String) : Flow<List<Capital>> {
        return if(country == ""){
            capitalRepository.getAllCapitals()
        }
        else{
            capitalRepository.getCapitalsByCountry(country)
        }
    }
}