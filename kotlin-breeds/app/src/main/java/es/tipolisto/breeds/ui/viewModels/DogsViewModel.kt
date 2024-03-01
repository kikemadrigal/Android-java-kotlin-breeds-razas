package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.providers.DogProvider
import es.tipolisto.breeds.data.repositories.DogRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.ui.states.DogScreenState
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class DogsViewModel: ViewModel() {
    var stateLives by mutableStateOf(5)
        private set
    var stateScore by mutableStateOf(0)
        private set
    var state by mutableStateOf(DogScreenState())
        private set
    var stateListRandomDogs by mutableStateOf(DogScreenState().stateListRandomDogs)
    var isFavorite by mutableStateOf(false)
        private set

    fun get3RamdomDogs(){
        viewModelScope.launch {
            stateListRandomDogs.set(0, DogRepository.getRandomDogFromBuffer(stateListRandomDogs))
            stateListRandomDogs.set(1, DogRepository.getRandomDogFromBuffer(stateListRandomDogs))
            stateListRandomDogs.set(2, DogRepository.getRandomDogFromBuffer(stateListRandomDogs))
            /*Log.d("TAG", "DogViewModel: dog 1->${stateListRandomDogs[0]?.name}, ${stateListRandomDogs[0]?.reference_image_id}")
            Log.d("TAG", "DogViewModel: dog 2->${stateListRandomDogs[1]?.name}, ${stateListRandomDogs[1]?.reference_image_id}")
            Log.d("TAG", "DogViewModel: dog 3->${stateListRandomDogs[2]?.name}, ${stateListRandomDogs[2]?.reference_image_id}")
*/
            state.correctAnswer= Random.nextInt(0..2)
            //Log.d("TAG", "DogViewModel: El elegido es el ${state.correctAnswer}->${stateListRandomDogs[state.correctAnswer]?.name}, ${stateListRandomDogs[state.correctAnswer]?.reference_image_id}")

            state=state.copy(
                stateListRandomDogs=stateListRandomDogs
            )
        }
    }

    fun getDogCorrectAnswer(): Dog?{
        return state.stateListRandomDogs[state.correctAnswer]
    }


    fun checkCorrectAnswer(answer:Int){
        Log.d("TAG", "GogViewModel: Has pinchado en el radiobutton "+answer)
        if(answer==state.correctAnswer){
            stateScore+=10
        }
        else {
            stateLives--
        }
        get3RamdomDogs()
    }

    fun checkGameOver():Boolean{
        return stateLives==0
    }

    fun getDogByReferenceImageId(referenceImageId:String):Dog?{
        Log.d("TAG", "DogViewModel dice: Vamos a ver hay un gato con este breed id "+referenceImageId)
        return DogRepository.getDogFromBreedIdInBuffer(referenceImageId)
    }

    fun getAllDog(){
        viewModelScope.launch {
            if(DogProvider.listDogs.isEmpty()) Log.d("TAG", "la lista de perros está vacía")
            else{
                DogProvider.listDogs.forEach {
                    //Log.d("TAG", it.name+", "+it.url )
                }
            }

        }
    }


    fun createFavorite(context: Context, idBreed: Int){
        //Creamos el favorito a partir del idBreed
        val favorite= FavoritesEntity(null, idBreed.toString(), "Fish", Date().toString())
        //val favorite= FavoritesRepository.getById(context,id)
        FavoritesRepository.insert(context,favorite)
        //FavoritesRepository.insert(context,favorite)
        Log.d("TAG","FisViewModel die: preparada el id: "+idBreed+"para añadir a favoritos a "+favorite.toString())
        isFavorite=true
    }
}