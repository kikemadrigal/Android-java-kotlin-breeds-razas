package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.providers.FishProvider
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.states.FavoritesScreenState
import es.tipolisto.breeds.ui.states.FishScreenState
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class FavoritesViewModel: ViewModel() {
    var state by mutableStateOf(FavoritesScreenState())
        private set
    //var stateListFavorites by mutableStateOf(FavoritesScreenState().stateListFavorites)
    var stateListFavorites= emptyList<FavoritesEntity>()
    var stateHeard by mutableStateOf(FavoritesScreenState().stateHeard)
    fun updateFavoritesList(context: Context){
        viewModelScope.launch {
            stateListFavorites=FavoritesRepository.getAll(context).toMutableStateList()
            /*state=state.copy(
                stateListFavorites=stateListFavorites
            )*/
        }
    }


    fun createFavorite(context: Context, idBreed: Int){
        //Creamos el favorito a partir del idBreed
        val favorite=FavoritesEntity(null, idBreed.toString(), "Fish", Date().toString())
        //val favorite= FavoritesRepository.getById(context,id)
        FavoritesRepository.insert(context,favorite)
        //FavoritesRepository.insert(context,favorite)
        Log.d("TAG","FisViewModel die: preparada el id: "+idBreed+"para añadir a favoritos a "+favorite.toString())
    }
}