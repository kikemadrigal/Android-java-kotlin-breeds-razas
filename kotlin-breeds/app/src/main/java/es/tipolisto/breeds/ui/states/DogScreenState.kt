package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog

data class DogScreenState(
    val stateListRandomDogs: MutableList<Dog?> = mutableListOf(null, null, null),
    var correctAnswer:Int=0,
    var isLoading:Boolean=false
)
