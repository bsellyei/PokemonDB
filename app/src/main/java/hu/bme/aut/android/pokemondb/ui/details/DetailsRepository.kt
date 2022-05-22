package hu.bme.aut.android.pokemondb.ui.details

import androidx.annotation.WorkerThread
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.model.persistence.PokemonId
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
    @WorkerThread
    fun getPokemon(id: Long) = flow {
        var fromDb: PokemonDto? = null
        getPokemonFromDb(
            id,
            { p -> fromDb = p }
        )

        if (fromDb != null) {
            emit(fromDb!!)
        } else {
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
            insertPokemon(pokemon)
            emit(pokemon)
        }
    }.flowOn(Dispatchers.IO)

    private fun getPokemonFromDb(
        id: Long,
        onSuccess: (PokemonDto?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val pokemon = pokemonDao.getPokemon(id)
            if (pokemon == null) {
                onSuccess(null)
            } else {
                onSuccess(createDto(pokemon))
            }
        }
    }

    private fun insertPokemon(pokemon: PokemonDto) {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonDao.insertPokemon(createDbPokemon(pokemon))
        }
    }

    fun updatePokemon(pokemon: PokemonDto) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = createDbPokemon(pokemon)
            pokemonDao.updatePokemon(db)
        }
    }

    fun deletePokemon(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonDao.deletePokemon(PokemonId(id))
        }
    }

    private fun createDbPokemon(
        pokemon: PokemonDto
    ): Pokemon {
        return Pokemon(
            id = pokemon.id,
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
            officialArtwork = pokemon.officialArtwork
        )
    }

    private fun createDto(
        pokemon: Pokemon
    ): PokemonDto {
        return PokemonDto(
            id = pokemon.id,
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
            officialArtwork = pokemon.officialArtwork
        )
    }
}