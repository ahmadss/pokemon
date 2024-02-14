package com.testedot.pokemon.presentation.di

import com.testedot.pokemon.data.api.PokemonApiService
import com.testedot.pokemon.data.repository.datasource.PokemonRemoteDataSource
import com.testedot.pokemon.data.repository.datasourceimpl.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun providePokemonRemoteDataSource(pokemonApiService: PokemonApiService): PokemonRemoteDataSource{
        return PokemonRemoteDataSourceImpl(pokemonApiService)
    }
}