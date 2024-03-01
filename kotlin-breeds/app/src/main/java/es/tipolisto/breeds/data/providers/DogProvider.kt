package es.tipolisto.breeds.data.providers

import es.tipolisto.breeds.data.models.dog.Dog


class DogProvider {
    companion object{
        var listDogs:List<Dog> = emptyList()
    }
}