package hu.bme.aut.android.pokemondb.mock.network

import hu.bme.aut.android.pokemondb.model.network.*
import hu.bme.aut.android.pokemondb.network.PokemonService
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockPokemonService : PokemonService {
    override fun getGeneration(generationId: String): Call<GenerationResult> {
        val generationResult = GenerationResult(
            pokemon_species = listOf(Name(
                name = "bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon-species/1/"
            ))
        )

        return makeCall(generationResult)
    }

    override fun getPokemon(pokemonName: String): Call<Pokemon> {
        val pokemon = Pokemon(
            id = 1,
            name = "bulbasaur",
            baseExperience = 20,
            height = 8,
            weight = 15,
            order = 0,
            sprites = Sprite("", "", SpriteOther(OfficialArtwork(""))),
            stats = listOf(Stat(35, Name("hp", ""))),
            types = listOf(Types(0, Name("grass", "")))
        )

        return makeCall(pokemon)
    }

    override fun addPokemon(pokemon: Pokemon): Call<Pokemon> {
        return makeCall(pokemon)
    }

    override fun updatePokemon(pokemonName: String, pokemon: Pokemon): Call<Void> {
        return makeCall()
    }

    override fun deletePokemon(pokemonName: String): Call<Void> {
        return makeCall()
    }

    private fun makeCall(generationResult: GenerationResult): Call<GenerationResult> {
        return object : Call<GenerationResult> {
            override fun clone(): Call<GenerationResult> = this

            override fun execute(): Response<GenerationResult> = Response.success(generationResult)

            override fun enqueue(callback: Callback<GenerationResult>) { }

            override fun isExecuted(): Boolean = false

            override fun cancel() { }

            override fun isCanceled() = false

            override fun request() = null

            override fun timeout() = Timeout.NONE
        }
    }

    private fun makeCall(): Call<Void> {
        return object : Call<Void> {
            override fun clone(): Call<Void> = this

            override fun execute(): Response<Void> = Response.success(null)

            override fun enqueue(callback: Callback<Void>) { }

            override fun isExecuted(): Boolean = false

            override fun cancel() { }

            override fun isCanceled() = false

            override fun request() = null

            override fun timeout() = Timeout.NONE
        }
    }

    private fun makeCall(pokemon: Pokemon): Call<Pokemon> {
        return object : Call<Pokemon> {
            override fun clone(): Call<Pokemon> = this

            override fun execute(): Response<Pokemon> = Response.success(pokemon)

            override fun enqueue(callback: Callback<Pokemon>) { }

            override fun isExecuted(): Boolean = false

            override fun cancel() { }

            override fun isCanceled() = false

            override fun request() = null

            override fun timeout() = Timeout.NONE
        }
    }
}