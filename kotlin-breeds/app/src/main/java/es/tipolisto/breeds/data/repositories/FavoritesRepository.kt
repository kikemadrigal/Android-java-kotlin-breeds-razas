package es.tipolisto.breeds.data.repositories

import android.content.Context
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.database.records.RecordEntity

class FavoritesRepository() {

    companion object{
        /*
        fun checkIsFavorite(id:Int, context: Context):Boolean{
            val isFavorite=false
            val favoriteDao=AppDataBaseClient.getFavoritesDao(context)
            val favorite=favoriteDao.getFavoriteById(id)
            when(favorite.animal){
                "Cat"->{

                }
            }
            return isFavorite
        }
*/
        fun getAll(context: Context):List<FavoritesEntity>{
            return AppDataBaseClient.getFavoritesDao(context).getAll()
        }


        fun getById(context: Context, id:Int):FavoritesEntity{
            return AppDataBaseClient.getFavoritesDao(context).getFavoriteById(id)
        }


        fun insert(context :Context,favoritesEntity: FavoritesEntity){
            AppDataBaseClient.getFavoritesDao(context = context).insert(favoritesEntity)
        }

    }


}