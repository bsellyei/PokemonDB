package hu.bme.aut.android.pokemondb.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.pokemondb.model.network.GenerationResult
import hu.bme.aut.android.pokemondb.model.network.Name
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var pokemons: GenerationResult

    init {
        pokemons = GenerationResult(Name("", ""))
    }

    fun getPokemons() {
        pokemons = mainRepository.getPokemons()
    }
}