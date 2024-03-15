package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.cat.Cat

data class CatsScreenState(
    val listRandomCats: MutableList<Cat?> = mutableListOf(null, null, null),
    var lives:Int=5,
    var score:Int=0,
    var correctAnswer:Int=0,
    //var isLoading:Boolean=false
)



