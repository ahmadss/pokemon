package com.testedot.pokemon.presentation.di

import com.testedot.pokemon.data.db.PokemonDao
import com.testedot.pokemon.data.repository.datasource.PokemonLocalDataSource
import com.testedot.pokemon.data.repository.datasourceimpl.PokemonLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(pokemonDao: PokemonDao):PokemonLocalDataSource{
        return PokemonLocalDataSourceImpl(pokemonDao)
    }
}