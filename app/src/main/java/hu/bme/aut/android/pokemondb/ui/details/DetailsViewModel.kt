package hu.bme.aut.android.pokemondb.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Name
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.model.persistence.PokemonId
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val pokemonId: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    var pokemon = pokemonId.flatMapLatest {
        detailsRepository.getPokemon(it)
    }

    fun getPokemon(id: Long) = pokemonId.tryEmit(id)

    fun updatePokemon(toUpdate: PokemonDto) {
        detailsRepository.updatePokemon(toUpdate)
        getPokemon(toUpdate.id!!)
    }

    fun deletePokemon(id: Long) {
        detailsRepository.deletePokemon(id)
    }
}