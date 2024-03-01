package es.tipolisto.breeds.ui.states

import es.tipolisto.breeds.data.database.favorites.FavoritesEntity
import es.tipolisto.breeds.data.models.fish.Fish

data class FavoritesScreenState(
    val stateListFavorites: MutableList<FavoritesEntity?> = mutableListOf(),
    val stateHeard: Boolean=false
)
