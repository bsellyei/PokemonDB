package hu.bme.aut.android.pokemondb.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    val imageLoader: ImageLoader
) : ViewModel() {

    var pokemons: Flow<List<PokemonDto>> = getPokemonsByGeneration()

    fun getPokemonsByGeneration(): Flow<List<PokemonDto>> {
        return mainRepository.getGeneration(
            generationId = "1",
            onError = { message -> print(message) }
        )
    }
}