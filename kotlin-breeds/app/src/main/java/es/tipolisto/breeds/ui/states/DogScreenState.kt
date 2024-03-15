package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog

data class DogScreenState(
    val listRandomDogs: MutableList<Dog?> = mutableListOf(null, null, null),
    var lives:Int=5,
    var score:Int=0,
    var correctAnswer:Int=0
)
