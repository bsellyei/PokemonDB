package hu.bme.aut.android.pokemondb.persistence

import androidx.room.*
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemon(id: Long): Pokemon

    @Insert
    fun insertPokemon(vararg pokemon: Pokemon)

    @Update
    fun updatePokemon(vararg pokemon: Pokemon)

    @Delete
    fun deletePokemon(pokemon: Pokemon)
}