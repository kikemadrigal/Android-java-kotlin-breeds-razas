package es.tipolisto.breeds.data.network;

import java.util.List;

import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.Cat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICatsApi {
    //Web: https://docs.thecatapi.com/
    //Url base:https://api.thecatapi.com/v1/

    //Devuelve un array de objetos catListResponse (que contienen un monton de Strings un objeto Image), devuelve una lista de objetos CallListResponse
    @GET("breeds")
    Call<List<Cat>> getAllCats();

    //Devuelve una lista con un objeto cat a partir del id que le mandamos por la url (el id lo yenemos ya predefinido en un hasmap)
    //https://api.thecatapi.com/v1/images/search?breed_ids=3
    @GET("images/search")
    Call<List<CatSimple>> getSimpleCatById(@Query("breed_ids") String breed_id);

}
