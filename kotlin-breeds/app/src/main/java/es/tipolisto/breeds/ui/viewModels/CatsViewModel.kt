package es.tipolisto.breeds.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.repositories.CatRepository
import es.tipolisto.breeds.data.repositories.FavoritesRepository
import es.tipolisto.breeds.data.repositories.FishRepository
import es.tipolisto.breeds.ui.states.CatsScreenState
import es.tipolisto.breeds.ui.states.FishScreenState
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class CatsViewModel: ViewModel() {
    var stateLives by mutableStateOf(5)
        private set
    var stateScore by mutableStateOf(0)
        private set
    var result=false
        private set
    var state by mutableStateOf(CatsScreenState())
        private set
    var stateListRandomCats by mutableStateOf(CatsScreenState().stateListRandomCats)
    //var stateIsloading by mutableStateOf(CatsScreenState().isLoading)
    //Para que solo se carga una vez la lista de internet
    var justOnce by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    suspend fun loadAndInsertBuffer(){
        isLoading=true
        CatRepository.loadCatsAndInsertBuffer()
        isLoading=false
    }
    fun get3RamdomCats(){
        viewModelScope.launch {
            //var stateListRandomCats= state.stateListRandomCats
            stateListRandomCats.set(0, CatRepository.getRandomCatFromBuffer(stateListRandomCats))
            stateListRandomCats.set(1, CatRepository.getRandomCatFromBuffer(stateListRandomCats))
            stateListRandomCats.set(2, CatRepository.getRandomCatFromBuffer(stateListRandomCats))
            Log.d("TAG", "CatViewModel: cat 1->${stateListRandomCats[0]?.name}, ${stateListRandomCats[0]?.reference_image_id}")
            Log.d("TAG", "CatViewModel: cat 2->${stateListRandomCats[1]?.name}, ${stateListRandomCats[1]?.reference_image_id}")
            Log.d("TAG", "CatViewModel: cat 3->${stateListRandomCats[2]?.name}, ${stateListRandomCats[2]?.reference_image_id}")
        
            state.correctAnswer= Random.nextInt(0..2)
            Log.d("TAG", "CatViewModel: El elegido es el ${state.correctAnswer}->${stateListRandomCats[state.correctAnswer]?.name}, ${stateListRandomCats[state.correctAnswer]?.reference_image_id}")

            state=state.copy(
                stateListRandomCats=stateListRandomCats
            )
        }
    }

    fun getCatCorrectAnswer(): Cat?{
        return state.stateListRandomCats[state.correctAnswer]
    }

    /**
    fun get3RamdomBreedsDogs(): Array<BreedsDog?>? {
    val dogs: Array<BreedsDog?> = arrayOfNulls<BreedsDog>(3)
    dogs[0] = getDogUsesCase.getRandomBreedsDogFromBuffer()
    dogs[1] = getDogUsesCase.getRandomBreedsDogFromBuffer()
    dogs[2] = getDogUsesCase.getRandomBreedsDogFromBuffer()
    return dogs
    }
     */
    fun checkCorrectAnswer(answer:Int):Boolean{
        var result:Boolean=false
        Log.d("TAG", "CatViewModel: Has pinchado en el radiobutton "+answer)
        if(answer==state.correctAnswer){
            result=true
            stateScore+=10
        }
        else {
            result=false
            stateLives--
        }
        get3RamdomCats()
        return result
    }

    fun checkGameOver():Boolean{
        return stateLives==0
    }

    fun getCatByImageCat(referenceImageId:String):Cat?{
        Log.d("TAG", "CatViewModel dice: Vamos a ver hay un gato con este breed id "+referenceImageId)
         return CatRepository.getCatFromIdImageCatInBuffer(referenceImageId)
    }


    fun createFavorite(context: Context, idBreed: String){
        //Creamos el favorito a partir del idBreed
        val favorite= FavoritesEntity(null, idBreed, "Cat", Date().toString())
        //val favorite= FavoritesRepository.getById(context,id)
        FavoritesRepository.insert(context,favorite)
        //FavoritesRepository.insert(context,favorite)
        Log.d("TAG","CatViewModel die: el id: "+idBreed+" añadido a favoritos, tostring: "+favorite.toString())
        //isFavorite=true
    }
}











