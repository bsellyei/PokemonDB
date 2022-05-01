package hu.bme.aut.android.pokemondb.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "height") var height: Int?,
    @ColumnInfo(name = "weight") var weight: Int?,
    @ColumnInfo(name = "baseExp") var baseExp: Int?,
    @ColumnInfo(name = "types") var types: String?,
    @ColumnInfo(name = "hp") var hp: Int?,
    @ColumnInfo(name = "attack") var attack: Int?,
    @ColumnInfo(name = "defense") var defense: Int?,
    @ColumnInfo(name = "specialAttack") var specialAttack: Int?,
    @ColumnInfo(name = "specialDefense") var specialDefense: Int?,
    @ColumnInfo(name = "speed") var speed: Int?,
    @ColumnInfo(name = "officialArtwork") var officialArtwork: String?
)
