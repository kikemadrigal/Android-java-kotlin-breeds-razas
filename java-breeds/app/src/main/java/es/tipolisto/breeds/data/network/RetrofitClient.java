package es.tipolisto.breeds.data.network;

import java.util.List;

import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.DogResponse;
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
        IDogApi iDogApiService=retrofit.create(IDogApi.class);
        return iDogApiService;
    }

    public ICatsApi getCatApiService(){
        retrofit=createRetrofit("https://api.thecatapi.com/v1/");
        ICatsApi iCatsApiService=retrofit.create(ICatsApi.class);
        return iCatsApiService;
    }

    public Cat getCat(String breed_id){return null;}

    public List<Cat> getListCat(){return null;}

    public DogResponse getDog(){return null;}

    public List<DogResponse> getListDog(){return null;};




}
