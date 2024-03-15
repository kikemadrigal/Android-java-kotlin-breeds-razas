package es.tipolisto.breeds.data.database.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.tipolisto.breeds.data.database.records.RecordEntity


@Dao
interface FavoritesDao {

    @Query("SELECT * FROM FavoritesEntity")
    fun getAll(): List<FavoritesEntity>
    @Query("SELECT * FROM FavoritesEntity WHERE animal='Cat' ORDER BY nameBreed")
    fun getAllFavoritesOnlyCats(): List<FavoritesEntity>
    @Query("SELECT * FROM FavoritesEntity WHERE animal='Dog' ORDER BY nameBreed")
    fun getAllFavoritesOnlyDogs(): List<FavoritesEntity>
    @Query("SELECT * FROM FavoritesEntity WHERE animal='Fish' ORDER BY nameBreed")
    fun getAllFavoritesOnlyFish(): List<FavoritesEntity>




    @Query("SELECT * FROM FavoritesEntity WHERE idAnimal=(:id)")
    fun getFavoriteById(id: String): FavoritesEntity?
    @Query("SELECT * FROM FavoritesEntity WHERE idAnimal IN (:id) ORDER BY nameBreed")
    fun getFavoriteByIdAnimal(id: String): List<FavoritesEntity>




    @Insert
    fun insert(favoritesEntity: FavoritesEntity)
    @Insert
    fun insertAll(vararg favoritesEntity: FavoritesEntity)

    @Update
    fun update(favoritesEntity: FavoritesEntity)

    @Delete
    fun delete(favoritesEntity: FavoritesEntity)


}