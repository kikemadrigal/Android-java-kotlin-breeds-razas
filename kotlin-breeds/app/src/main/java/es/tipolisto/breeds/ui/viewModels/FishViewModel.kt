package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.providers.FishProvider
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.states.FishScreenState
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class FishViewModel: ViewModel() {
    var stateLives by mutableStateOf(5)
        private set
    var stateScore by mutableStateOf(0)
        private set
    var state by mutableStateOf(FishScreenState())
        private set
    var stateListRandomFish by mutableStateOf(FishScreenState().stateListRandomFish)

    var isFavorite by mutableStateOf(false)
        private set
    fun get3RamdomFish(){
        viewModelScope.launch {
            stateListRandomFish.set(0, FishRepository.getRandomFishFromBuffer(stateListRandomFish))
            stateListRandomFish.set(1, FishRepository.getRandomFishFromBuffer(stateListRandomFish))
            stateListRandomFish.set(2, FishRepository.getRandomFishFromBuffer(stateListRandomFish))
            Log.d("TAG", "FishViewModel: fish 1->${stateListRandomFish[0]?.name}, ${stateListRandomFish[0]?.id}")
            Log.d("TAG", "FishViewModel: fish 2->${stateListRandomFish[1]?.name}, ${stateListRandomFish[1]?.id}")
            Log.d("TAG", "FishViewModel: fish 3->${stateListRandomFish[2]?.name}, ${stateListRandomFish[2]?.id}")

            state.correctAnswer= Random.nextInt(0..2)
            Log.d("TAG", "FishViewModel: El elegido es el ${state.correctAnswer}->${stateListRandomFish[state.correctAnswer]?.name}, ${stateListRandomFish[state.correctAnswer]?.id}")

            state=state.copy(
                stateListRandomFish=stateListRandomFish
            )
        }
    }

    fun getFishCorrectAnswer(): Fish?{
        return state.stateListRandomFish[state.correctAnswer]
    }


    fun checkCorrectAnswer(answer:Int){
        Log.d("TAG", "GogViewModel: Has pinchado en el radiobutton "+answer)
        if(answer==state.correctAnswer){
            stateScore+=10
        }
        else {
            stateLives--
        }
        get3RamdomFish()
    }

    fun checkGameOver():Boolean{
        return stateLives==0
    }

    fun getFishByIdFish(id:Int): Fish?{
        Log.d("TAG", "FishViewModel dice: Vamos a ver hay un gato con este breed id "+id)
        return FishRepository.getFishFromSpecieIdInBuffer(id)
    }

    fun createFavorite(context: Context, idBreed: Int){
        //Creamos el favorito a partir del idBreed
        val favorite=FavoritesEntity(null, idBreed.toString(), "Fish", Date().toString())
        //val favorite= FavoritesRepository.getById(context,id)
        FavoritesRepository.insert(context,favorite)
        //FavoritesRepository.insert(context,favorite)
        Log.d("TAG","FisViewModel die: preparada el id: "+idBreed+"para añadir a favoritos a "+favorite.toString())
        isFavorite=true
    }
}