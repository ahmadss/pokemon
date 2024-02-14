package com.testedot.pokemon.domain.usecase

import com.testedot.pokemon.data.model.PokemonApiResponse
import com.testedot.pokemon.data.util.Resource
import com.testedot.pokemon.domain.repository.PokemonRepository

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(offset:Int, limit:Int): Resource<PokemonApiResponse>{
        return pokemonRepository.getPokemonList(offset, limit)
    }
}