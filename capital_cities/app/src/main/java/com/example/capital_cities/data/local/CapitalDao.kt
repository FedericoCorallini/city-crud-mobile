package com.example.capital_cities.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CapitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCapital(capital: CapitalEntity)

    @Query("SELECT * FROM capitalentity WHERE country = :country")
    fun getCapitalsByCountry(country : String) : Flow<List<CapitalEntity>>

    @Query("SELECT * FROM capitalentity WHERE name = :name")
    fun getCapitalsByName(name : String) : Flow<List<CapitalEntity>>

    @Query("SELECT * FROM capitalentity")
    fun getAllCapitals() : Flow<List<CapitalEntity>>

    @Query("DELETE FROM capitalentity WHERE country = :country")
    suspend fun deleteAllByCountry(country : String)

    @Delete
    suspend fun delete(capital: CapitalEntity)

    @Query("UPDATE capitalentity SET population = :newPopulation WHERE id = :id")
    suspend fun updatePopulation(id: Int, newPopulation: Long)
}