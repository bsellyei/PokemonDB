package hu.bme.aut.android.pokemondb.ui.details

import androidx.annotation.WorkerThread
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
    @WorkerThread
    fun getPokemon(id: Long) = flow {
        val response = pokemonService.getPokemon(id.toString()).execute()
        val body = response.body()!!
        val types: String = body.types!!.map { it.type!!.name!! }.toString()
        val stats: Map<String, Int> = body.stats!!.associate { it.stat!!.name!! to it.baseStat!! }.toMap()

        val pokemon = PokemonDto(
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
        emit(pokemon)
    }.flowOn(Dispatchers.IO)

    /*fun getPokemon(id: Long): Pokemon {
        return pokemonDao.getPokemon(id)
    }*/

    fun updatePokemon(pokemon: Pokemon) {
        pokemonDao.updatePokemon(pokemon)
    }

    fun deletePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemon(pokemon)
    }
}