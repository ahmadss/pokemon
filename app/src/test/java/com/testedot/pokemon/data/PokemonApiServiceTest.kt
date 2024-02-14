package com.testedot.pokemon.data

import com.google.common.truth.Truth
import com.testedot.pokemon.data.api.PokemonApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat


class PokemonApiServiceTest {
    private lateinit var service: PokemonApiService
    private lateinit var serve: MockWebServer

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        serve = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(serve.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }

    @org.junit.jupiter.api.Test
    fun PokemonAPIService_sentRequest_receivedExpexted() {
        runBlocking {
            enqueueMockResponse("pokemonresponse.json")
            val responseBody = service.getPokemonList(0, 10).body()
            val request = serve.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/pokemon?offset=0&limit=10")
        }
    }

    @org.junit.jupiter.api.Test
    fun getPokemon_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("pokemonresponse.json")
            val responseBody = service.getPokemonList(0,10).body()
            val pokemonList = responseBody!!.results
            assertThat(pokemonList.size).isEqualTo(10)
        }
    }

    @org.junit.jupiter.api.Test
    fun getPokemon_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("pokemonresponse.json")
            val responseBody = service.getPokemonList(0,10).body()
            val pokemonList = responseBody!!.results
            val pokemon = pokemonList[0]
            assertThat(pokemon.name).isEqualTo("ivysaur")
            assertThat(pokemon.url).isEqualTo("https://pokeapi.co/api/v2/pokemon/2/")
        }
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        serve.enqueue(mockResponse)
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
        serve.shutdown()
    }
}