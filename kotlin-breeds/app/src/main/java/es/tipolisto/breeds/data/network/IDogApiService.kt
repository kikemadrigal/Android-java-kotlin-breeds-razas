package es.tipolisto.breeds.data.network

import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.dog.ImageDog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IDogApiService {
    //API1 Web: https://www.thedogapi.com/ Url base: https://api.thedogapi.com/v1/ api_key=dc1e51b2-d3e2-4f59-89a6-0a67fba096ac
    //-------------------------------------------------------------------------------------------------------------------------

    /*
    api_key=live_j5vqrzrFkOhf1AsiXwprmdoKWyTqokjM3hFVZ4cIOU0AKwM01F7FUxlZn1ppN3JZ
    Listar todas las razas: https://api.thedogapi.com/v1/breeds
    Listar todas las razas con un límite= https://api.thedogapi.com/v1/images/search?limit=100&order=Desc
    Obtener datos raza sin imagen según el id de una raza: https://api.thedogapi.com/v1/breeds/search?name=Akita
    Obtener datos raza sin imagen según el nombre de una raza: https://api.thedogapi.com/v1/breeds/search?name=Akita
    Obtener id, url, width y height según el nombre de la raza: https://api.thedogapi.com/v1/images/search?breed_name=beng
    Obtener una imagen aleatoria: https://api.thedogapi.com/v1/images/search
     */


    /*1 obtenemos una lista de objectos Dog con https://api.thedogapi.com/v1/breeds
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

    //2 por cada id obtenemos su url a partir del breed_id https://api.thedogapi.com/v1/images/search?bree_id=HJ7Pzg5EQ
    {
        "id": "wRJ9Dwspw",
        "url": "https://cdn2.thedogapi.com/images/wRJ9Dwspw.jpg",
        //Estas 2 ultimas no las queremos
        "width": 1280,
        "height": 1920
    }
     */

    //https://api.thedogapi.com/v1/breeds: devuelve un lista detalla de las razas sin la imagen
    @GET("breeds")
    suspend fun getListBreedsDog():Response<List<Dog>>

    //Devuelve una imagen de un perro a partir del breed_id
    //https://api.thedogapi.com/v1/images/search?bree_id=HJ7Pzg5EQ
    @GET("images/search")
    suspend fun getDataDogBreedById(@Query("bree_id") reference_image_id:String):Response<List<ImageDog>>








    //Devuelve una imagen de la raza de perro que corresponde al nombre pasado en la Url
    //https://api.thedogapi.com/v1/images/search?bree_id=HJ7Pzg5EQ
    //No se utiliza
    @GET("images/search")
    fun getDataDogBreedByName(@Query("name") name:String):Response<List<Dog>>

    //https://api.thedogapi.com/v1/images/search
    //Devuelve un objeto imagen aleatoria de tipo list<Dog>
    //No se utiliza
    @GET("images/search")
    fun getDog():Response<List<Dog>>







    //                                  API 2:  URL base: https://dog.ceo/api/");
    //------------------------------------------------------------------------------------------------------------------------------

    /*

    //Obtención de una imagen aleatoria, la raza está incluida el el objeto DogREsponse que contiene el path con la URL de la imagen
    //https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random")
    Call<DogResponseApi2> getDog();

    //Obtención de una imagen aleatoria de una raza concreta
    //https://dog.ceo/api/breed/hound/images/random
    @GET("breed/hound/images/random")
    Call<DogListResponseApi2> getDogByBreeds();

    //Obtención de un objeto que contiene una lista de imágenes de cualquier raza, 100 es el número de imagánes a devolver
    //https://dog.ceo/api/
    @GET("breeds/image/random/100")
    Call<DogListResponseApi2> getListDog();

    //Obtención de un objeto ue contiene una lista de imágenes de una raza concreta y un String con la respuesta
    @GET("breed/hound/images")
    Call<DogListResponseApi2> getListDogByBreeds();


     */

}