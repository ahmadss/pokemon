package com.testedot.pokemon.presentation.di

import com.testedot.pokemon.presentation.adapter.PokemonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Provides
    @Singleton
    fun providePokemonAdapter(): PokemonAdapter {
        return PokemonAdapter()
    }
}