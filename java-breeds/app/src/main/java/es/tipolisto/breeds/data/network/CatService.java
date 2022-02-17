package es.tipolisto.breeds.data.network;

import android.util.Log;

import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.model.DogResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatService {
    private RetrofitClient retrofitClient;
    public CatService (){
        retrofitClient=new RetrofitClient();
    }

    public List<CatListResponse> getAllCats(){
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        Call<List<CatListResponse>> callCatListResponse=iCatsApiService.getAllCats();
        callCatListResponse.enqueue(new Callback<List<CatListResponse>>() {
            @Override
            public void onResponse(Call<List<CatListResponse>> call, Response<List<CatListResponse>> response) {
                if(response.isSuccessful()){
                    List<CatListResponse> listCats=response.body();
                    //Log.d("Mensaje", "recividos: "+listCats.size());
                    ArrayDataSource.listAllcats=response.body();
                }
            }
            @Override
            public void onFailure(Call<List<CatListResponse>> call, Throwable t) {
                ArrayDataSource.listAllcats=null;
            }
        });
        return ArrayDataSource.listAllcats;
    }


    public Cat getCatByBreed(String breedId){
        ICatsApi iCatsApiService= retrofitClient.getCatApiService();
        //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
        Call<List<Cat>> callCat=iCatsApiService.getcatById(breedId);
        callCat.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                    List<Cat>listCat=response.body();
                    if (listCat.size()>0)
                        ArrayDataSource.cat=listCat.get(0);
                    else
                        ArrayDataSource.cat=null;
                }
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                ArrayDataSource.cat=null;
            }
        });
        return ArrayDataSource.cat;
    }

    public Cat getdataBreedCat(String breedId){
        //Obtenemos la imagen
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
        Call<List<Cat>> callCat=iCatsApiService.getcatById(breedId);
        callCat.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                    //La url en realidad devuelve un array con 1 solo objeto cat
                    List<Cat> listcats=response.body();
                    if(listcats.size()>0){
                        ArrayDataSource.cat=listcats.get(0);
                    }else{
                        ArrayDataSource.cat=null;
                    }

                }
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                ArrayDataSource.cat=null;
            }
        });
        return  ArrayDataSource.cat;
    }
}
