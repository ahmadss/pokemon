package com.testedot.pokemon.data.repository.datasourceimpl

import com.testedot.pokemon.data.db.PokemonDao
import com.testedot.pokemon.data.model.Result
import com.testedot.pokemon.data.repository.datasource.PokemonLocalDataSource
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSourceImpl(private val pokemonDao: PokemonDao) : PokemonLocalDataSource {

    override suspend fun savePokemonToDB(pokemon: Result) {
        pokemonDao.insert(pokemon)
    }

    override fun getSavePokemons(): Flow<List<Result>> {
        return pokemonDao.getAllPokemons()
    }


}