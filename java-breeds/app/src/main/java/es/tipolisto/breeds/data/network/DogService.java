package es.tipolisto.breeds.data.network;

import android.util.Log;

import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.model.DogResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogService {
    private RetrofitClient retrofitClient;
    public DogService(){
        retrofitClient=new RetrofitClient();
    }

    public List<DogResponse> getAllDogs(){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<DogResponse>> callListDogResponse=iDogApiService.getListDog();
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    //List<DogResponse> listDogResponse=response.body();
                    //Log.d("Mensaje", "recividos: "+listDogResponse.size());
                    ArrayDataSource.listAllDogs=response.body();
                    //Log.d("Mensaje", "tamaño lista perros "+listDogResponse.size());
                }
            }

            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {
                ArrayDataSource.listAllDogs=null;
            }
        });
        return ArrayDataSource.listAllDogs;
    }



    public DogResponse getDogByBreed(){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<DogResponse>> callListDogResponse=iDogApiService.getDog();
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    List<DogResponse> listDogResponse=response.body();
                    Log.d("Mensaje", "DogService "+listDogResponse.size());
                    if (listDogResponse.size()>0){
                        ArrayDataSource.dogResponse = listDogResponse.get(0);
                    }else{
                        ArrayDataSource.dogResponse=null;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {
                Log.d("Mensaje","Hubo un fallo");
                ArrayDataSource.dogResponse=null;
            }
        });
        return ArrayDataSource.dogResponse;
    }



    public DogResponse getDataDog(String breedId){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<DogResponse>> callListDogResponse=iDogApiService.getDataDogBreed(breedId);
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    List<DogResponse> listDogResponse=response.body();
                    if(listDogResponse.size()>0){
                        DogResponse dogResponse=listDogResponse.get(0);
                        ArrayDataSource.dogResponse=dogResponse;
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {

            }
        });
        return ArrayDataSource.dogResponse;
    }
}
