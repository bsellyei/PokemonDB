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
        generationId: String = "1",
        onResponse: (GenerationResult) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
         pokemonService.getGeneration(generationId).enqueue(object : Callback<GenerationResult> {
            override fun onResponse(call: Call<GenerationResult>, response: Response<GenerationResult>) {
                onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<GenerationResult>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    fun getPokemon(
        pokemonName: String,
        onResponse: (Pokemon) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        pokemonService.getPokemon(pokemonName).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}