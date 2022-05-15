package hu.bme.aut.android.pokemondb.ui.main

import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Pokemon
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
    fun getPokemons(
        generationId: String = "1"
    ): GenerationResult {
        val response = pokemonService.getGeneration(generationId).execute()
        return response.body()!!
    }

    fun getPokemon(
        pokemonName: String
    ): Pokemon {
        val response = pokemonService.getPokemon(pokemonName).execute()
        return response.body()!!
    }
}