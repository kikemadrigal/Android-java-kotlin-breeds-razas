package es.tipolisto.breeds.utils

import android.Manifest

class Constants {
    companion object{
        const val URL_BASE_CAT="https://api.thecatapi.com/v1/"
        const val URL_BASE_DOG="https://api.thedogapi.com/v1/"
        const val URL_BASE_FISH="https://fish-species.p.rapidapi.com/fish_api/"
        const val KEY_API_FISH="mira en los keys"
        val CAMERAX_PERMISSIONS= arrayOf(Manifest.permission.CAMERA)
    }

}