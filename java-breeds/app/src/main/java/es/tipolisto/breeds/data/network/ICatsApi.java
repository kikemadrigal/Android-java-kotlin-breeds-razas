package es.tipolisto.breeds.data.network;

import java.util.List;

import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.ImageCat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICatsApi {
    //Web: https://docs.thecatapi.com/
    //Url base:https://api.thecatapi.com/v1/

    //Devuelve un array de objetos catListResponse (que contienen un monton de Strings un objeto Image), devuelve una lista de objetos CallListResponse
    //https://api.thecatapi.com/v1/breeds
    @GET("breeds")
    Call<List<Cat>> getAllCats();


    @GET("images/search")
    Call<List<CatSimple>> getSimpleCatById(@Query("breed_ids") String breed_id);
    //@GET("images")
    @GET("images/{reference_image_id}")
    //Devuelve una Image de un cat dado un reference_image_id que es una propiedad de un objeto Cat:
    //https://api.thecatapi.com/v1/images/4lXnnfxac
    Call<ImageCat> getImageCatById(@Path("reference_image_id") String reference_image_id);
}
