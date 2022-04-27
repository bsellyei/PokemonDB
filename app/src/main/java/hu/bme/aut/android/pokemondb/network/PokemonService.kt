package hu.bme.aut.android.pokemondb.network

import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Pokemon
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {
    @GET("/generation/{generationId}")
    fun getGeneration(@Path("generationId") generationId: String): Call<GenerationResult>

    @GET("/pokemon/{pokemonName}")
    fun getPokemon(@Path("pokemonName") pokemonName: String): Call<Pokemon>

    @POST("/pokemon")
    fun addPokemon(@Body pokemon: Pokemon): Call<Pokemon>

    @PUT("/pokemon/{pokemonName}")
    fun updatePokemon(@Path("pokemonName") pokemonName: String, @Body pokemon: Pokemon): Call<Void>

    @DELETE("/pokemon/{pokemonName}")
    fun deletePokemon(@Path("pokemonName") pokemonName: String): Call<Void>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2"
    }
}