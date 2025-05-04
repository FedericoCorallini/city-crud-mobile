package com.example.capital_cities.data.mapper

import com.example.capital_cities.data.local.CapitalEntity
import com.example.capital_cities.domain.model.Capital

fun Capital.toEntity() : CapitalEntity {
    return CapitalEntity(
        id = id,
        name = name,
        country = country,
        population = population
    )
}

fun CapitalEntity.toDomain() : Capital {
    return Capital(
        id = id,
        name = name,
        country = country,
        population = population
    )
}
