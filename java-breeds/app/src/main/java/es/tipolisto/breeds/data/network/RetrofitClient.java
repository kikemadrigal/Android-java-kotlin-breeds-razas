package es.tipolisto.breeds.data.network;

import java.util.List;

import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.Dog;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit=null;
    public RetrofitClient(){}

    private Retrofit createRetrofit(String baseUrl){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public IDogApi getDogApiService(){
        retrofit = createRetrofit("https://api.thedogapi.com/v1/");
        return retrofit.create(IDogApi.class);
    }

    public ICatsApi getCatApiService(){
        retrofit=createRetrofit("https://api.thecatapi.com/v1/");
        return retrofit.create(ICatsApi.class);
    }

    public CatSimple getCat(String breed_id){return null;}

    public List<CatSimple> getListCat(){return null;}

    public Dog getDog(){return null;}

    public List<Dog> getListDog(){return null;};




}
