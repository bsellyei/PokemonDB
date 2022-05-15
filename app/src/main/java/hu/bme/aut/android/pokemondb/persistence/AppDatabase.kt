package hu.bme.aut.android.pokemondb.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.pokemondb.model.persistence.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DB_NAME = "pokemon_database"
    }
}