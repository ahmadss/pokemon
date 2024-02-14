package com.testedot.pokemon.data.api

import com.testedot.pokemon.data.model.PokemonApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("/api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset")
        offset:Int,
        @Query("limit")
        limit:Int
    ): Response<PokemonApiResponse>
}