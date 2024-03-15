package es.tipolisto.breeds.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.tipolisto.breeds.data.database.favorites.FavoritesDao
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.ui.states.FavoritesScreenState
import java.util.Date


class FavoritesViewModel(private val favoritesDao: FavoritesDao): ViewModel() {
    var state by mutableStateOf(FavoritesScreenState())
        private set
    var stateHeard by mutableStateOf(FavoritesScreenState().stateHeard)
    //var isFavorite by mutableStateOf(false)
    private val _isFavorite= MutableLiveData<Boolean>()
    var isFavorite: LiveData<Boolean> = _isFavorite
    fun getAll():List<FavoritesEntity>{
        val listFavorites=FavoritesRepository.getAll(favoritesDao).toMutableStateList()
        state=state.copy(
            listFavorites=listFavorites
        )
        return listFavorites
    }
    fun getAllCats():List<FavoritesEntity>{
        val listFavorites=FavoritesRepository.getAllOnlyCats(favoritesDao).toMutableStateList()
        state=state.copy(
            listFavorites=listFavorites
        )
        return listFavorites
    }
    fun getAllDogs():List<FavoritesEntity>{
        val listFavorites=FavoritesRepository.getAllOnlyDogs(favoritesDao).toMutableStateList()
        state=state.copy(
            listFavorites=listFavorites
        )
        return listFavorites
    }
    fun getAllFish():List<FavoritesEntity>{
        val listFavorites=FavoritesRepository.getAllOnlyFish(favoritesDao).toMutableStateList()
        state=state.copy(
            listFavorites=listFavorites
        )
        return listFavorites
    }


    fun createFavorite(cat: Cat){
        //Creamos el favorito a partir del idBreed
        val favorite=FavoritesEntity(null, cat.id, cat.name, "Cat", cat.image.url,cat.description, Date().toString())
        //val favorite= FavoritesRepository.getById(context,id)
        FavoritesRepository.insert(favoritesDao,favorite)
        //FavoritesRepository.insert(context,favorite)
        //Log.d("TAG","FisViewModel die: preparada el id: "+idBreed+"para añadir a favoritos a "+favorite.toString())
    }
    fun createFavorite(dog: Dog){
        val favorite=FavoritesEntity(null, dog.id.toString(), dog.name, "Dog", dog.imageDog?.url?:"Not image",dog.bred_for, Date().toString())
        FavoritesRepository.insert(favoritesDao,favorite)
    }
    fun createFavorite(fish: Fish?){
        if(fish!=null){
            val favorite=FavoritesEntity(null, fish.id.toString(), fish.name, "Fish", fish.img_src_set?:"Not image",fish.meta.toString(), Date().toString())
            FavoritesRepository.insert(favoritesDao,favorite)
        }
    }

    fun checkIsInFavorites(idAnimal:String){
        _isFavorite.value=false
        _isFavorite.value=FavoritesRepository.isFavorite(favoritesDao,idAnimal)
    }

    fun setFavorite(isFavorite:Boolean){
        _isFavorite.value=isFavorite
    }


    fun getFavoritesByIdAnimal(idAnimal:String):List<FavoritesEntity>{
        return FavoritesRepository.getByIdAnimal(favoritesDao,idAnimal)

    }

    fun delete(favoritesEntity: FavoritesEntity) {
        FavoritesRepository.delete(favoritesDao,favoritesEntity)
    }

    fun clickFavorite(){
        _isFavorite.value=!_isFavorite.value!!
    }
}