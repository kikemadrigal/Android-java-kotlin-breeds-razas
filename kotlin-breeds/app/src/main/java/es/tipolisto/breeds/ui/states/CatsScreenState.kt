package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.cat.Cat

data class CatsScreenState(
    val stateListRandomCats: MutableList<Cat?> = mutableListOf(null, null, null),
    var correctAnswer:Int=0,
    var isLoading:Boolean=false
)



