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

    //Devuelve todas los ids
    //https://api.thecatapi.com/v1/breeds

    //Devuelve una lista con un objeto cat a partir del id que le mandamos por la url (consulta hashmap)
    //https://api.thecatapi.com/v1/images/search?breed_ids=dons
    @GET("images/search")
    Call<List<Cat>> getcatById(@Query("breed_ids") String breed_id);

    //Devulve descripcion y datos de una raza
    //https://api.thecatapi.com/v1/breeds/search?name=donskoy&api_key=4c16cae4-2d03-4e0d-8c71-09e88c6e768f


}
