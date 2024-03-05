package es.tipolisto.breeds.utils

sealed class AudioEffectsType(val name:String){
    object typeClick:AudioEffectsType("click")
    object typeSuccess:AudioEffectsType("success")
    object typeFail:AudioEffectsType("failure")
}