package com.testedot.pokemon.domain.repository

import com.testedot.pokemon.data.model.PokemonApiResponse
import com.testedot.pokemon.data.util.Resource

interface PokemonRepository {

    suspend fun getPokemonList(offset:Int, limit:Int):Resource<PokemonApiResponse>
}