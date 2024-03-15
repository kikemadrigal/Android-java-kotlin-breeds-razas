package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.fish.Fish

data class FishScreenState(
    val listRandomFish: MutableList<Fish?> = mutableListOf(null, null, null),
    var lives:Int=5,
    var score:Int=0,
    var correctAnswer:Int=0
)
