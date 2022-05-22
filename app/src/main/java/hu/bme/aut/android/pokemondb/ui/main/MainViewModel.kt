package hu.bme.aut.android.pokemondb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.pokemondb.dto.PokemonDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var pokemons: MutableLiveData<List<PokemonDto>> = MutableLiveData()

    init {
        getInitial()
    }

    private fun getInitial() {
        mainRepository.getGeneration(
            generationId = "1",
            onSuccess = {
                val sorted = it.sortedBy { p -> p.id }
                pokemons.value = sorted
            },
            onError = { message -> print(message) },
        )
    }

    fun search(generationSearch: Boolean, value: String) {
         if (generationSearch) {
             val category = convertSearchCategory(value)
             if (category.isNotEmpty()) {
                 mainRepository.getGeneration(
                     generationId = category,
                     onSuccess = {
                         pokemons.value = it
                     },
                     onError = { message -> print(message) },
                 )
             }
        } else {
            mainRepository.getSinglePokemon(
                pokemonName = value,
                onSuccess = {
                    pokemons.value = listOf(it)
                },
                onError = { message -> print(message) }
            )
        }
    }

    fun add(
        pokemon: PokemonDto
    ) {
        mainRepository.add(pokemon)
    }

    private fun convertSearchCategory(
        category: String
    ): String {
        when(category) {
            "Generation I" -> return "1"
            "Generation II" -> return "2"
            "Generation III" -> return "3"
            "Generation IV" -> return "4"
            "Generation V" -> return "5"
            "Generation VI" -> return "6"
            "Generation VII" -> return "7"
            "Generation VIII" -> return "8"
        }

        return ""
    }
}