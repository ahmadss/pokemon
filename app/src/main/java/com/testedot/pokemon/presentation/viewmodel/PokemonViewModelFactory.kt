package com.testedot.pokemon.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testedot.pokemon.domain.usecase.GetPokemonListUseCase

class PokemonViewModelFactory(private val application: Application,
                              private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonViewModel(
            application,
            getPokemonListUseCase
        ) as T
    }
}