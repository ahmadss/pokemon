package com.testedot.pokemon.presentation.di

import android.app.Application
import androidx.room.Room
import com.testedot.pokemon.data.db.PokemonDao
import com.testedot.pokemon.data.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(app, PokemonDatabase::class.java, "pokemon_db")
            .fallbackToDestructiveMigration()
            .build()
    }
//
    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.getPokemonDao()
    }
}