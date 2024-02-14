package com.testedot.pokemon.presentation.di

import android.app.Application
import com.testedot.pokemon.domain.usecase.GetPokemonListUseCase
import com.testedot.pokemon.presentation.viewmodel.PokemonViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providePokemonViewModelFactory(
        application: Application,
        getPokemonListUseCase: GetPokemonListUseCase
    ): PokemonViewModelFactory {
        return PokemonViewModelFactory(
            application,
            getPokemonListUseCase
        )
    }

}