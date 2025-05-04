package com.example.capital_cities.di

import android.content.Context
import androidx.room.Room
import com.example.capital_cities.data.local.CapitalDao
import com.example.capital_cities.data.local.CapitalDatabase
import com.example.capital_cities.data.repository.CapitalRepositoryImpl
import com.example.capital_cities.domain.repository.CapitalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context : Context) : CapitalDao {
        return Room.databaseBuilder(
            context = context,
            CapitalDatabase::class.java,
            "capital_db"
        ).build().dao
    }

    @Provides
    @Singleton
    fun provideRepository(capitalDao: CapitalDao) : CapitalRepository {
        return CapitalRepositoryImpl(capitalDao = capitalDao)
    }
}