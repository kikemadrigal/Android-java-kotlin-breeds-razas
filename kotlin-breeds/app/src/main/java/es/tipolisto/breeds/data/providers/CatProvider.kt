package es.tipolisto.breeds.data.providers

import es.tipolisto.breeds.data.models.cat.Cat

class CatProvider {
    companion object{
        var listCats:List<Cat> = emptyList()
    }
}