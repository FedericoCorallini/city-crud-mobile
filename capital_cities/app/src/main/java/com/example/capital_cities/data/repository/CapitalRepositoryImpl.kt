package com.example.capital_cities.data.repository

import com.example.capital_cities.data.local.CapitalDao
import com.example.capital_cities.data.mapper.toDomain
import com.example.capital_cities.data.mapper.toEntity
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.domain.repository.CapitalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CapitalRepositoryImpl(
    private val capitalDao: CapitalDao
) : CapitalRepository {
    override suspend fun insertCapital(capital: Capital) {
        capitalDao.insertCapital(capital = capital.toEntity())
    }

    override fun getCapitalsByCountry(country: String): Flow<List<Capital>> {
        return capitalDao.getCapitalsByCountry(country).map { it.map { it.toDomain()} }
    }

    override fun getAllCapitals() : Flow<List<Capital>> {
        return capitalDao.getAllCapitals().map { it.map { it.toDomain()} }
    }
}