package com.testedot.pokemon.data.repository.datasourceimpl

import com.testedot.pokemon.data.api.PokemonApiService
import com.testedot.pokemon.data.model.PokemonApiResponse
import com.testedot.pokemon.data.repository.datasource.PokemonRemoteDataSource
import retrofit2.Response

class PokemonRemoteDataSourceImpl(private val pokemonApiService: PokemonApiService) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonApiResponse> {
        return pokemonApiService.getPokemonList(offset, limit)
    }
}