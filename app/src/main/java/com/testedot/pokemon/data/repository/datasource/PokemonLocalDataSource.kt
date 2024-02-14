package com.testedot.pokemon.data.repository.datasource

import com.testedot.pokemon.data.model.Result
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    suspend fun savePokemonToDB(pokemon: Result)

    fun getSavePokemons() : Flow<List<Result>>
}
