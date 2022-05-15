package hu.bme.aut.android.pokemondb.ui.details

import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {

    fun getPokemon(id: Long): Pokemon {
        return pokemonDao.getPokemon(id)
    }

    fun updatePokemon(pokemon: Pokemon) {
        pokemonDao.updatePokemon(pokemon)
    }

    fun deletePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemon(pokemon)
    }
}