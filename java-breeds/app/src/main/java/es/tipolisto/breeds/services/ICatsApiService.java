package es.tipolisto.breeds.services;

import java.util.List;

import es.tipolisto.breeds.model.Cat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICatsApiService {
    //Web: https://docs.thecatapi.com/
    //Url base:https://api.thecatapi.com/v1/


    //Devuelve un array de objetos cat (que contienen un monton de Strings un objeto Image)
    //https://api.thecatapi.com/v1/breeds/?api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f
    @GET("breeds/?api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f")
    Call<List<Cat>> getAllCats();

    //Se obtiene un objeto cat a partir de la raza que le mandamos por la url
    //https://api.thecatapi.com/v1/images/search?api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f&breed_name=Burmilla
    //@GET("images/search?api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f&breed_name=Burmilla")
    @GET("images/search?api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f")
    Call<List<Cat>> getcatByBreed(@Query("name") String breed_name);

    //buscar raza por nombre
    //https://api.thecatapi.com/v1/breeds/search?name=donskoy&api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f
}
