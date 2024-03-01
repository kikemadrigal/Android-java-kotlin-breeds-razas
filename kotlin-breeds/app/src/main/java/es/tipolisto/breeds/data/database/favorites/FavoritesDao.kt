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

    @Query("SELECT * FROM FavoritesEntity WHERE id=(:id)")
    fun getFavoriteById(id: Int): FavoritesEntity

    @Query("SELECT * FROM FavoritesEntity WHERE idBreed IN (:idBreed)")
    fun getFavoriteByIdBreed(idBreed: String): List<FavoritesEntity>

    @Insert
    fun insert(favoritesEntity: FavoritesEntity)

    @Insert
    fun insertAll(vararg favoritesEntity: FavoritesEntity)

    @Update
    fun update(favoritesEntity: FavoritesEntity)

    @Delete
    fun delete(favoritesEntity: FavoritesEntity)


}