package com.testedot.pokemon.data.repository.datasource

import com.testedot.pokemon.data.model.PokemonApiResponse
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(offset:Int, limit:Int): Response<PokemonApiResponse>
}