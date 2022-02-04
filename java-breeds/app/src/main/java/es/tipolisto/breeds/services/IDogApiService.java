package es.tipolisto.breeds.services;

import es.tipolisto.breeds.model.DogListResponse;
import es.tipolisto.breeds.model.DogResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IDogApiService {

    //URL base: https://dog.ceo/api/");

    //Obtención de una imagen aleatoria, la raza está incluida el el objeto DogREsponse que contiene el path con la URL de la imagen
    //https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random")
    Call<DogResponse> getDog();

    //Obtención de una imagen aleatoria de una raza concreta
    //https://dog.ceo/api/breed/hound/images/random
    @GET("breed/hound/images/random")
    Call<DogListResponse> getDogByBreeds();

    //Obtención de un objeto que contiene una lista de imágenes de cualquier raza, 100 es el número de imagánes a devolver
    //https://dog.ceo/api/
    @GET("breeds/image/random/100")
    Call<DogListResponse> getListDog();

    //Obtención de un objeto ue contiene una lista de imágenes de una raza concreta y un String con la respuesta
    @GET("breed/hound/images")
    Call<DogListResponse> getListDogByBreeds();




}
