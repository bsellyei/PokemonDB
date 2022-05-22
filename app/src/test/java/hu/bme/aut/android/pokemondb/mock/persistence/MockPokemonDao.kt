package hu.bme.aut.android.pokemondb.mock.persistence

import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.persistence.PokemonDao

class MockPokemonDao: PokemonDao {
    private val pokemon = Pokemon(
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

    override fun getAllPokemon(): List<Pokemon> = listOf(pokemon)

    override fun getPokemon(id: Long): Pokemon = pokemon

    override suspend fun insertPokemon(vararg pokemon: Pokemon) { }

    override suspend fun updatePokemon(vararg pokemon: Pokemon) { }

    override suspend fun deletePokemon(pokemon: Pokemon) { }
}