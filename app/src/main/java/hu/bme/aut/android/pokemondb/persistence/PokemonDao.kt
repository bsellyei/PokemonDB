package hu.bme.aut.android.pokemondb.persistence

import androidx.room.*
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon
import hu.bme.aut.android.pokemondb.model.persistence.PokemonId

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemon(id: Long): Pokemon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg pokemon: Pokemon)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePokemon(vararg pokemon: Pokemon)

    @Delete(entity = Pokemon::class)
    suspend fun deletePokemon(vararg pokemon: PokemonId)
}