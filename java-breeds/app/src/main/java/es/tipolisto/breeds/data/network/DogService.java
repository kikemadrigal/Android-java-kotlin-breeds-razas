package es.tipolisto.breeds.data.network;

import android.util.Log;

import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSourceProvider;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Aunque están declaradas los servicios, tan solo se utiliza el de obtener todos los perros
 * Ya que se almacenan en un buffer que después utilizamos
 */
public class DogService {
    private RetrofitClient retrofitClient;
    public DogService(){
        retrofitClient=new RetrofitClient();
    }

    public List<Dog> getAllDogs(){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<Dog>> callListDogResponse=iDogApiService.getListDog();
        callListDogResponse.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                //List<Dog> listDogResponse=response.body();
                if(response.isSuccessful()){
                    //Log.d("Mensaje", "recividos: "+listDogResponse.size());
                    ArrayDataSourceProvider.listAllDogs=response.body();
                }else{
                    Log.d("Mensaje", "No se recibió nada");
                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                ArrayDataSourceProvider.listAllDogs=null;
            }
        });
        return ArrayDataSourceProvider.listAllDogs;
    }
    public List<BreedsDog> getAllBreedDogs(){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<BreedsDog>> callListDogResponse=iDogApiService.getListBreedDog();
        callListDogResponse.enqueue(new Callback<List<BreedsDog>>() {
            @Override
            public void onResponse(Call<List<BreedsDog>> call, Response<List<BreedsDog>> response) {
                if(response.isSuccessful()){
                    //Log.d("Mensaje", "recividos: "+listDogResponse.size());
                    ArrayDataSourceProvider.listAllBreedDogs=response.body();
                }else{
                    Log.d(Constants.LOG, "No se recibió nada");
                }
            }

            @Override
            public void onFailure(Call<List<BreedsDog>> call, Throwable t) {
                ArrayDataSourceProvider.listAllBreedDogs=null;
            }
        });
        return ArrayDataSourceProvider.listAllBreedDogs;
    }




    public Dog getDogByBreed(){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<Dog>> callListDogResponse=iDogApiService.getDog();
        callListDogResponse.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if(response.isSuccessful()){
                    List<Dog> listDogResponse=response.body();
                    Log.d("Mensaje", "DogService "+listDogResponse.size());
                    if (listDogResponse.size()>0){
                        ArrayDataSourceProvider.dog = listDogResponse.get(0);
                    }else{
                        ArrayDataSourceProvider.dog=null;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Log.d("Mensaje","Hubo un fallo");
                ArrayDataSourceProvider.dog=null;
            }
        });
        return ArrayDataSourceProvider.dog;
    }



    public Dog getDataDog(String breedName){
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<Dog>> callListDogResponse=iDogApiService.getDataDogBreedByName(breedName);
        callListDogResponse.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if(response.isSuccessful()){
                    List<Dog> listDogResponse=response.body();
                    if(listDogResponse.size()>0){
                        Dog dogResponse=listDogResponse.get(0);
                        ArrayDataSourceProvider.dog=dogResponse;
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {

            }
        });
        return ArrayDataSourceProvider.dog;
    }
}

/*
    Pinta del objeto Dog
    private List<BreedsDog> breeds;
    private String id;
    private String url;
    private int width;
    private int height;

 */
