package es.tipolisto.breeds.data.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "idBreed") val idBreed: String,
    @ColumnInfo(name = "animal") val animal: String,
    @ColumnInfo(name = "date") val date: String
)
