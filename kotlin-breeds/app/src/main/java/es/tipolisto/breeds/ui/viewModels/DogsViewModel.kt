package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.providers.CatProvider
import es.tipolisto.breeds.data.providers.DogProvider
import es.tipolisto.breeds.data.repositories.DogRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.states.DogScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class DogsViewModel: ViewModel() {
    var state by mutableStateOf(DogScreenState())
        private set
    var justOnce by mutableStateOf(false)
    var stateIsLoading by mutableStateOf(false)
    var gameOver by mutableStateOf(false)
        private set
    var clickPressed by mutableStateOf(false)
    var clicksToExit=0


    suspend fun loadAndInsertBuffer(){
        stateIsLoading=true
        DogRepository.loadDogAndInsertBuffer()
        gameOver=false
        stateIsLoading=false
    }

    fun initGame() {
        gameOver=false
        state.lives=5
        state.score=0
    }
    fun getAll():List<Dog>{
        return DogProvider.listDogs
    }

    fun get3RamdomDogs(){
        viewModelScope.launch {
            if (clickPressed) delay(2000)
            val listRandomDogs= mutableListOf<Dog?>(null, null, null)
            listRandomDogs[0] = DogRepository.getRandomDogFromBuffer(listRandomDogs)
            listRandomDogs[1] = DogRepository.getRandomDogFromBuffer(listRandomDogs)
            listRandomDogs[2] = DogRepository.getRandomDogFromBuffer(listRandomDogs)
            state=state.copy(
                listRandomDogs=listRandomDogs
            )
            state.correctAnswer= Random.nextInt(0..2)
            clickPressed=false
            /*Log.d("TAG", "DogViewModel: dog 1->${stateListRandomDogs[0]?.name}, ${stateListRandomDogs[0]?.reference_image_id}")
            Log.d("TAG", "DogViewModel: dog 2->${stateListRandomDogs[1]?.name}, ${stateListRandomDogs[1]?.reference_image_id}")
            Log.d("TAG", "DogViewModel: dog 3->${stateListRandomDogs[2]?.name}, ${stateListRandomDogs[2]?.reference_image_id}")
*/

            Log.d("TAG", "DogViewModel: El elegido es el ${state.correctAnswer}->${state.listRandomDogs[state.correctAnswer]?.name}, ${state.listRandomDogs[state.correctAnswer]?.reference_image_id}")
        }
    }

    fun getActiveDog(): Dog?{
        return state.listRandomDogs[state.correctAnswer]
    }


    fun checkCorrectAnswer(answer:Int){
        //Log.d("TAG", "GogViewModel: Has pinchado en el radiobutton "+answer)
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

    fun getDogByReferenceImageId(referenceImageId:String):Dog?{
        Log.d("TAG", "DogViewModel dice: Vamos a ver hay un gato con este breed id "+referenceImageId)
        return DogRepository.getDogFromBreedIdInBuffer(referenceImageId)
    }

}