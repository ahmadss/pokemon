package com.testedot.pokemon.presentation.di

import com.testedot.pokemon.data.PokemonRepositoryImpl
import com.testedot.pokemon.data.repository.datasource.PokemonLocalDataSource
import com.testedot.pokemon.data.repository.datasource.PokemonRemoteDataSource
import com.testedot.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        pokemonLocalDataSource: PokemonLocalDataSource
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonRemoteDataSource, pokemonLocalDataSource)
    }
}