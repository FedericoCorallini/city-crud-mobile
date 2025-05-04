package com.example.capital_cities.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CapitalEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val name : String,
    val country : String,
    val population : Long
)