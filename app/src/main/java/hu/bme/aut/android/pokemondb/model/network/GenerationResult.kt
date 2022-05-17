package hu.bme.aut.android.pokemondb.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationResult(
    val pokemon_species: List<Name>?
)
