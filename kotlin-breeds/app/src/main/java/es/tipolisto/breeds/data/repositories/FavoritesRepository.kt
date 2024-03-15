package es.tipolisto.breeds.data.repositories

import android.content.Context
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.favorites.FavoritesDao
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.database.records.RecordEntity

class FavoritesRepository() {

    companion object{

        fun isFavorite(dao: FavoritesDao,id:String):Boolean{
            var isFavorite=false
            val favoritesEntity=dao.getFavoriteById(id)
            if(favoritesEntity!=null) isFavorite=true
            return isFavorite
        }

        fun getByIdAnimal(dao:FavoritesDao, id:String):List<FavoritesEntity>{
            return dao.getFavoriteByIdAnimal(id)
        }
        fun getAllOnlyCats(dao:FavoritesDao):List<FavoritesEntity>{
            return dao.getAllFavoritesOnlyCats()
        }
        fun getAllOnlyDogs(dao:FavoritesDao):List<FavoritesEntity>{
            return dao.getAllFavoritesOnlyDogs()
        }
        fun getAllOnlyFish(dao:FavoritesDao):List<FavoritesEntity>{
            return dao.getAllFavoritesOnlyFish()
        }

        fun getAll(dao: FavoritesDao):List<FavoritesEntity>{
            return dao.getAll()
        }




        fun insert(dao: FavoritesDao,favoritesEntity: FavoritesEntity){
            dao.insert(favoritesEntity)
        }

        fun delete(dao: FavoritesDao,favoritesEntity: FavoritesEntity){
            dao.delete(favoritesEntity)
        }

    }

}