package hu.bme.aut.android.pokemondb.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Name
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var pokemon: Pokemon

    init {
        pokemon = Pokemon(
            id = 1,
            name = "bulbasaur",
            height = 8,
            weight = 15,
            baseExp = 20,
            types = "grass",
            hp = 35,
            attack = 8,
            defense = 6,
            specialAttack = 4,
            specialDefense = 2,
            speed = 6,
            officialArtwork = "artwork"
        )
    }

    fun getPokemon() {
        pokemon = detailsRepository.getPokemon(1)
    }
}