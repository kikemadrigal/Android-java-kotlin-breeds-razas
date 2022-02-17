package es.tipolisto.breeds.domain;

import android.util.Log;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.network.ICatsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCatByBreedUsesCase {
    DataRepository dataRepository;
    public GetCatByBreedUsesCase(){
        dataRepository=new DataRepository();
    }

    public Cat getCatByBreed(String breedId){
       return dataRepository.getCatByBreed(breedId);
    }
}
