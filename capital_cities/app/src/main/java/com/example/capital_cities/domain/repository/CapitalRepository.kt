package com.example.capital_cities.domain.repository

import com.example.capital_cities.domain.model.Capital
import kotlinx.coroutines.flow.Flow

interface CapitalRepository {
    suspend fun insertCapital(capital: Capital)
    fun getCapitalsByName(name: String): Flow<List<Capital>>
    fun getAllCapitals() : Flow<List<Capital>>
    suspend fun deleteCapital(capital: Capital)
    suspend fun deleteAllByCountry(country: String)
    suspend fun updatePopulation(id: Int, newPopulation: Long)
}