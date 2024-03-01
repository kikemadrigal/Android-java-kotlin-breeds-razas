package es.tipolisto.breeds.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.tipolisto.breeds.data.database.favorites.FavoritesDao
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.database.records.RecordDao
import es.tipolisto.breeds.data.database.records.RecordEntity

@Database(entities = [RecordEntity::class, FavoritesEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
    abstract fun favoritesDao(): FavoritesDao
}