package hu.bme.aut.android.pokemondb.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int?,
    val name: String?,
    val height: Int?,
    val weight: Int?,
    @Json(name = "base_experience") val baseExperience: Int?,
    val order: Int?,
    val types: List<Types>?,
    val stats: List<Stat>?,
    val sprites: Sprite?
)

@JsonClass(generateAdapter = true)
data class Types(
    val slot: Int?,
    val type: Name?
)

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "base_stat") val baseStat: Int?,
    val stat: Name?
)

@JsonClass(generateAdapter = true)
data class Sprite(
    @Json(name = "back_default") val backDefault: String?,
    @Json(name = "front_default") val frontDefault: String?,
    val other: SpriteOther?
)

@JsonClass(generateAdapter = true)
data class SpriteOther(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork?
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @Json(name = "front_default") val front_default: String?
)
