package es.tipolisto.breeds.data.repositories

import android.util.Log
import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.dog.ImageDog
import es.tipolisto.breeds.data.network.RetrofitClient
import es.tipolisto.breeds.data.providers.CatProvider
import es.tipolisto.breeds.data.providers.DogProvider
import kotlin.random.Random

class DogRepository {
    companion object{
        suspend fun loadDogAndInsertBuffer() {
            val service= RetrofitClient.getRetroDogService()

            /**
             Obtenemos esto:
             [
                 {
                     "weight": {
                     "imperial": "6 - 13",
                     "metric": "3 - 6"
                 },
                     "height": {
                     "imperial": "9 - 11.5",
                     "metric": "23 - 29"
                 },
                 "id": 1,
                 "name": "Affenpinscher",
                 "bred_for": "Small rodent hunting, lapdog",
                 "breed_group": "Toy",
                 "life_span": "10 - 12 years",
                 "temperament": "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
                 "origin": "Germany, France",
                 "reference_image_id": "BJa4kxc4X"
                 },
                 {
                 ...
                 }
                 ...
             ]
             */
            val response=service.getListBreedsDog()
            val listBreedsDog =response.body()
            if(listBreedsDog!!.isEmpty()) Log.d("TAG", "Lista vacía")
            else{
                listBreedsDog.forEachIndexed {index, dog->
                    if(dog !=null){
                        /*
                         Obtenemos esto
                         [
                             {
                                 "id": "wRJ9Dwspw",
                                 "url": "https://cdn2.thedogapi.com/images/wRJ9Dwspw.jpg",
                                 "width": 1280,
                                 "height": 1920
                             }
                         ]
                        */
                        val responseImageCat=service.getDataDogBreedById(dog.reference_image_id)
                        val imageDog: List<ImageDog>? =responseImageCat.body()
                        if(imageDog!=null)
                            listBreedsDog.get(index).imageDog= imageDog[0]
                    }
                }
                DogProvider.listDogs=listBreedsDog
            }
        }

        fun getListDogFromBuffer()=DogProvider.listDogs

        fun getRandomDogFromBuffer(restricted:List<Dog?>): Dog?{
            var dog : Dog?=null
            if(!DogProvider.listDogs.isEmpty()){
                val random = Random.nextInt(DogProvider.listDogs.size)
                Log.d("TAG3", "DogRepository dice: el random es: "+random.toString())
                dog=DogProvider.listDogs[random]
                Log.d("TAG3", "DogRepository dice: el random es: "+dog.toString())
                for(i in restricted){
                    if(dog.name.equals(i?.name)) {
                        getRandomDogFromBuffer(restricted)
                    }
                }
            }else{
                Log.d("TAG3", "la lista esta vacia")
            }
            return dog
        }

        fun getDogFromBreedIdInBuffer(referenceBreedId: String):Dog?{
            var dog: Dog?=null
            DogProvider.listDogs.forEach{
                val reference=it.reference_image_id;
                if(reference!=null ){
                  if(reference.equals(referenceBreedId)) dog=it
                }

            }
            return dog
        }

        fun getDogFromIdInBuffer(id: String):Dog?{
            var dog: Dog?=null
            DogProvider.listDogs.forEach{
                val reference=it.id;
                if(reference!=null ){
                    if(reference.equals(id)) dog=it
                }

            }
            return dog
        }
    }//Final del cpompanion object

}