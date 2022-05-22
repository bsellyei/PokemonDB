package hu.bme.aut.android.pokemondb.ui.main

import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Pokemon
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
     fun getGeneration(
         generationId: String,
         onSuccess: (List<PokemonDto>) -> Unit,
         onError: (String) -> Unit
     ) {
        pokemonService.getGeneration(generationId).enqueue(object : Callback<GenerationResult> {
            override fun onFailure(call: Call<GenerationResult>, t: Throwable) {
                onError(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<GenerationResult>, response: Response<GenerationResult>) {
                val generation = response.body()!!
                val list = mutableListOf<PokemonDto>()
                generation.pokemon_species!!.forEach { pokemon ->
                    val components = pokemon.url.split("/")
                    getSinglePokemon(
                        pokemonName = components[6],
                        onSuccess = {
                            list.add(it)
                            onSuccess(list)
                        },
                        onError = onError)
                }
            }
        })
    }

    fun getSinglePokemon(
        pokemonName: String,
        onSuccess: (PokemonDto) -> Unit,
        onError: (String) -> Unit
    ) {
        pokemonService.getPokemon(pokemonName).enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                onError(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                val result = createPokemonDto(response.body()!!)
                onSuccess(result)
            }
        })
    }


    fun add(
        pokemon: PokemonDto
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonDao.insertPokemon(createDbPokemon(pokemon))
        }
    }

    private fun createDbPokemon(
        pokemon: PokemonDto
    ): hu.bme.aut.android.pokemondb.model.persistence.Pokemon {
        return hu.bme.aut.android.pokemondb.model.persistence.Pokemon(
            id = null,
            name = pokemon.name,
            weight = pokemon.weight,
            height = pokemon.height,
            types = pokemon.types,
            baseExp = pokemon.baseExp,
            hp = pokemon.hp,
            attack = pokemon.attack,
            defense = pokemon.defense,
            speed = pokemon.speed,
            specialAttack = pokemon.specialAttack,
            specialDefense = pokemon.specialDefense,
            officialArtwork = ""
        )
    }

    private fun createPokemonDto(
        pokemon: Pokemon
    ): PokemonDto {
        val types: String = pokemon.types!!.map { it.type!!.name!! }.toString()
        val stats: Map<String, Int> = pokemon.stats!!.associate { it.stat!!.name!! to it.baseStat!! }.toMap()

        return PokemonDto(
            id = pokemon.id!!.toLong(),
            name = pokemon.name!!,
            height = pokemon.height!!,
            weight = pokemon.weight!!,
            baseExp = pokemon.baseExperience!!,
            types = types,
            hp = stats["hp"],
            attack = stats["attack"],
            defense = stats["defense"],
            specialAttack = stats["special-attack"],
            specialDefense = stats["special-defense"],
            speed = stats["speed"],
            officialArtwork = pokemon.sprites!!.other!!.officialArtwork!!.front_default
        )
    }
}