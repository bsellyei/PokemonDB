package hu.bme.aut.android.pokemondb.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    val name: String?,
    val url: String
)
