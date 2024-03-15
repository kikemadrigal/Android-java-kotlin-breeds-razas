package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.providers.CatProvider
import es.tipolisto.breeds.data.providers.FishProvider
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.states.FishScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class FishViewModel: ViewModel() {
    var state by mutableStateOf(FishScreenState())
        private set
    var justOnce by mutableStateOf(false)
    //var stateIsLoading by mutableStateOf(FishScreenState().isLoading)
    var stateIsLoading by mutableStateOf(false)
    var gameOver by mutableStateOf(false)
        private set
    var clickPressed by mutableStateOf(false)
    var clicksToExit=0

    suspend fun loadAndInsertBuffer(){
        stateIsLoading=true
        FishRepository.loadFishAndInsertBuffer()
        gameOver=false
        stateIsLoading=false
    }
    fun initGame() {
        gameOver=false
        state.lives=5
        state.score=0
    }
    fun getAll():List<Fish>{
        return FishProvider.listFish
    }
    fun get3RamdomFish(){
        viewModelScope.launch {
            if (clickPressed) delay(2000)
            val listRandomFish= mutableListOf<Fish?>(null, null, null)
            listRandomFish[0] = FishRepository.getRandomFishFromBuffer(listRandomFish)
            listRandomFish[1] = FishRepository.getRandomFishFromBuffer(listRandomFish)
            listRandomFish[2] = FishRepository.getRandomFishFromBuffer(listRandomFish)
            state=state.copy(
                listRandomFish=listRandomFish
            )
            state.correctAnswer= Random.nextInt(0..2)
            clickPressed=false
            //Log.d("TAG", "FishViewModel: fish 1->${stateListRandomFish[0]?.name}, ${stateListRandomFish[0]?.id}")
            //Log.d("TAG", "FishViewModel: fish 2->${stateListRandomFish[1]?.name}, ${stateListRandomFish[1]?.id}")
            //Log.d("TAG", "FishViewModel: fish 3->${stateListRandomFish[2]?.name}, ${stateListRandomFish[2]?.id}")
            Log.d("TAG", "FishViewModel: El elegido es el ${state.correctAnswer}->${state.listRandomFish[state.correctAnswer]?.name}, ${state.listRandomFish[state.correctAnswer]?.id}")
        }
    }

    fun getActiveFish(): Fish?{
        return state.listRandomFish[state.correctAnswer]
    }


    fun checkCorrectAnswer(answer:Int){
        Log.d("TAG", "GogViewModel: Has pinchado en el radiobutton "+answer)
        if(answer==state.correctAnswer){
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

    fun getFishByIdFish(id:Int): Fish?{
        Log.d("TAG", "FishViewModel dice: Vamos a ver hay un gato con este breed id "+id)
        return FishRepository.getFishFromSpecieIdInBuffer(id)
    }


}