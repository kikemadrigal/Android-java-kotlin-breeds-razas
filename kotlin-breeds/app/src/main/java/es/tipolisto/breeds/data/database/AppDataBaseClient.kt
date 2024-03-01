package es.tipolisto.breeds.data.database

import android.content.Context
import androidx.room.Room
import es.tipolisto.breeds.data.database.favorites.FavoritesDao
import es.tipolisto.breeds.data.database.records.RecordDao

class AppDataBaseClient (){
    companion object{
        fun getAppDataBase(context: Context):AppDataBase{
            return Room.databaseBuilder(context = context, AppDataBase::class.java, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        fun getFavoritesDao(context : Context):FavoritesDao{
            return getAppDataBase(context).favoritesDao()
        }
        fun getRecordsDao(context : Context):RecordDao{
            return getAppDataBase(context).recordDao()
        }
    }
}