package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.fish.Fish

data class FishScreenState(
    val stateListRandomFish: MutableList<Fish?> = mutableListOf(null, null, null),
    var correctAnswer:Int=0,
    var isLoading:Boolean=false
)
