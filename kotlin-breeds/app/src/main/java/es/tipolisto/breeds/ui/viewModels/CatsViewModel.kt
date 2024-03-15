package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.AppDataBaseClient
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.data.providers.CatProvider
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.data.repositories.RecordsRepository
import es.tipolisto.breeds.ui.states.CatsScreenState
import es.tipolisto.breeds.ui.states.FishScreenState
import es.tipolisto.breeds.ui.views.MainActivity
import es.tipolisto.breeds.utils.MediaPlayerClient
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class CatsViewModel: ViewModel() {
    var state by mutableStateOf(CatsScreenState())
        private set
    //Para que solo se carga una vez la lista de internet
    var justOnce by mutableStateOf(false)
    //Para la barra de progreso
    var stateIsloading by mutableStateOf(false)
    var gameOver by mutableStateOf(false)
        private set
    var clickPressed by mutableStateOf(false)
    var clicksToExit=0

    suspend fun loadAndInsertBuffer(){
        stateIsloading=true
        CatRepository.loadCatsAndInsertBuffer()
        gameOver=false
        stateIsloading=false
    }
    fun initGame() {
        gameOver=false
        state.lives=5
        state.score=0
    }

    fun getAll():List<Cat>{
        return CatProvider.listCats
    }
    fun get3RamdomCats(){
        viewModelScope.launch {
            if (clickPressed)delay(2000)
            val listRandomCats= mutableListOf<Cat?>(null, null, null)
            listRandomCats[0] = CatRepository.getRandomCatFromBuffer(listRandomCats)
            listRandomCats[1] = CatRepository.getRandomCatFromBuffer(listRandomCats)
            listRandomCats[2] = CatRepository.getRandomCatFromBuffer(listRandomCats)
            state=state.copy(
                listRandomCats=listRandomCats
            )
            state.correctAnswer= Random.nextInt(state.listRandomCats.size)
            clickPressed=false
            //Log.d("TAG", "CatViewModel: cat 1->${stateListRandomCats[0]?.name}, ${stateListRandomCats[0]?.reference_image_id}")
            //Log.d("TAG", "CatViewModel: cat 2->${stateListRandomCats[1]?.name}, ${stateListRandomCats[1]?.reference_image_id}")
            //Log.d("TAG", "CatViewModel: cat 3->${stateListRandomCats[2]?.name}, ${stateListRandomCats[2]?.reference_image_id}")
            Log.d("TAG", "CatViewModel: El elegido es el ${state.correctAnswer}->${state.listRandomCats[state.correctAnswer]?.name}, ${state.listRandomCats[state.correctAnswer]?.reference_image_id}")
        }
    }
    //getActiveCat:Solo usada para obtener la imagen
    fun getActiveCat(): Cat?{
        return state.listRandomCats[state.correctAnswer]
    }



    fun checkCorrectAnswer(checkResponse:Int){
        if(checkResponse==state.correctAnswer){
            state.score+=10
        }
        else {
            state.lives--
            if(state.lives<=0){

                gameOver=true
            }
        }
    }

    @Composable
    private fun checkRecord() {


    }


    fun getCatByImageCat(referenceImageId:String):Cat?{
        Log.d("TAG", "CatViewModel dice: Vamos a ver hay un gato con este breed id "+referenceImageId)
        return CatRepository.getCatFromIdImageCatInBuffer(referenceImageId)
    }



}











