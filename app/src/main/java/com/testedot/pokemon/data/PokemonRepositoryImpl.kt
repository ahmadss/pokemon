package com.testedot.pokemon.data

import androidx.paging.ExperimentalPagingApi
import com.testedot.pokemon.data.model.PokemonApiResponse
import com.testedot.pokemon.data.repository.datasource.PokemonLocalDataSource
import com.testedot.pokemon.data.repository.datasource.PokemonRemoteDataSource
import com.testedot.pokemon.data.util.Resource
import com.testedot.pokemon.domain.repository.PokemonRepository
import retrofit2.Response

class PokemonRepositoryImpl(private val pokemonRemoteDatasource:PokemonRemoteDataSource, private val pokemonLocalDataSource: PokemonLocalDataSource) : PokemonRepository{

    override suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonApiResponse> {
        return responseToResource(pokemonRemoteDatasource.getPokemonList(offset, limit))
    }

    private fun responseToResource(response: Response<PokemonApiResponse>): Resource<PokemonApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }


}