package es.tipolisto.breeds.data.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.tipolisto.breeds.data.models.cat.BreedCat

@Entity
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "idAnimal") val idAnimal: String,
    @ColumnInfo(name = "nameBreed") val nameBreed: String,
    //Puede ser animal: cat, dog o fish
    @ColumnInfo(name = "animal") val animal: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "date") val date: String
)
