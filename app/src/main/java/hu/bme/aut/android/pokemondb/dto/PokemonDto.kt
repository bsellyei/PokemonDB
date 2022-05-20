package hu.bme.aut.android.pokemondb.dto

data class PokemonDto(
    var id: Long?,
    var name: String?,
    var height: Int?,
    var weight: Int?,
    var baseExp: Int?,
    var types: String?,
    var hp: Int?,
    var attack: Int?,
    var defense: Int?,
    var specialAttack: Int?,
    var specialDefense: Int?,
    var speed: Int?,
    var officialArtwork: String?
)
