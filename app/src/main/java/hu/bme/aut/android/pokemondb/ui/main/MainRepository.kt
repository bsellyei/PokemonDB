package hu.bme.aut.android.pokemondb.ui.main

import androidx.annotation.WorkerThread
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Pokemon
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
    @WorkerThread
    fun getGeneration(
        generationId: String,
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        try {
            val response = pokemonService.getGeneration(generationId).execute()
            val generation = response.body()!!
            val result: MutableList<PokemonDto> = mutableListOf()
            generation.pokemon_species!!.forEachIndexed { index, _ ->
                result.add(getPokemon((index+1).toString()))
            }
            emit(result)
        } catch(e: Exception) {
            onError(e.localizedMessage!!)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    private fun getPokemon(
        pokemonName: String
    ): PokemonDto {
        val response = pokemonService.getPokemon(pokemonName).execute()
        val body = response.body()!!
        val types: String = body.types!!.map { it.type!!.name!! }.toString()
        val stats: Map<String, Int> = body.stats!!.associate { it.stat!!.name!! to it.baseStat!! }.toMap()

        return PokemonDto(
            id = body.id!!.toLong(),
            name = body.name!!,
            height = body.height!!,
            weight = body.weight!!,
            baseExp = body.baseExperience!!,
            types = types,
            hp = stats["hp"],
            attack = stats["attack"],
            defense = stats["defense"],
            specialAttack = stats["special-attack"],
            specialDefense = stats["special-defense"],
            speed = stats["speed"],
            officialArtwork = body.sprites!!.other!!.officialArtwork!!.front_default
        )
    }
}