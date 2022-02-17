package es.tipolisto.breeds.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.network.ICatsApi;
import es.tipolisto.breeds.data.network.IDogApi;
import es.tipolisto.breeds.data.network.RetrofitClient;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.DogResponse;
import es.tipolisto.breeds.domain.GetDataCatUsesCase;
import es.tipolisto.breeds.domain.GetDataDogUsesCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedFragmentViewModel extends ViewModel {
    private MutableLiveData<Cat> mutableLiveDataCat;
    private MutableLiveData<DogResponse> mutableLiveDataDogresponse;
    private MutableLiveData<Boolean> mutableLiveDataProgressBarVisible;
    private RetrofitClient retrofitClient;
    private GetDataCatUsesCase getDataCatUsesCase;
    private GetDataDogUsesCase getDataDogUsesCase;

    public  BreedFragmentViewModel() {
        retrofitClient=new RetrofitClient();
        mutableLiveDataCat=new MutableLiveData<Cat>();
        mutableLiveDataProgressBarVisible=new MutableLiveData<Boolean>();
        mutableLiveDataDogresponse=new MutableLiveData<DogResponse>();
        getDataCatUsesCase=new GetDataCatUsesCase();
        getDataDogUsesCase=new GetDataDogUsesCase();
    }



    public MutableLiveData<Cat> getMutableLiveDataCat() {
        return mutableLiveDataCat;

    }

    public MutableLiveData<DogResponse> getMutableLiveDataDogresponse() {
        return mutableLiveDataDogresponse;
    }

    public MutableLiveData<Boolean> getMutableLiveDataProgressBarVisible() {
        return mutableLiveDataProgressBarVisible;
    }

    public String generateIdCat(String nameBreedCat){
        String idBreedCat="";
        HashMap<String, String> hasmapIdNames= ArrayDataSource.getMapCatIdNames();
        Iterator<String> iterator=hasmapIdNames.keySet().iterator();
        while(iterator.hasNext()){
            String llave=(String) iterator.next();
            String valueHasmMap =hasmapIdNames.get(llave);
            if (nameBreedCat.equalsIgnoreCase(valueHasmMap)){
                idBreedCat=llave;
            }
        }
        return idBreedCat;
    }

    public String generateIdDog(String breedName){
        //Para generar el id echamos mano de un hasmap donde están relaccionados todos los nombres con sus ids
        String breed_id="";
        HashMap<String,String> hashMapDogIdNames= ArrayDataSource.getMapDogIdNames();
        Iterator<String> iterator=hashMapDogIdNames.keySet().iterator();
        while(iterator.hasNext()){
            String llave=iterator.next();
            String value =hashMapDogIdNames.get(llave);
            //breed es la variable global obtenida desde el listfragment
            if(breedName.equalsIgnoreCase(value)){
                breed_id=llave;
            }
        }
        return breed_id;
    }


    public void getCat(String breedId){
        mutableLiveDataProgressBarVisible.postValue(true);
        /*
        Cat cat=getDataCatUsesCase.getDataCat(breedId);
        if(cat!=null){
            mutableLiveDataCat.postValue(cat);
            mutableLiveDataProgressBarVisible.postValue(false);
        }
        */


        //--------------------------------Esto es sin clean arquitecture----------------------
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
                        Cat cat =listcats.get(0);
                        Log.d("Menaje", "breedfragment ha listado"+String.valueOf(listcats.size()));
                        mutableLiveDataCat.postValue(cat);
                        mutableLiveDataProgressBarVisible.postValue(false);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Log.d("Mensaje","Mal");

            }
        });


    }




    public void getDog(String breedId){
        mutableLiveDataProgressBarVisible.postValue(true);
        /*
        DogResponse dogResponse= getDataDogUsesCase.getDataDog(breedId);
        if(dogResponse!=null){
            mutableLiveDataDogresponse.postValue(dogResponse);
            mutableLiveDataProgressBarVisible.postValue(false);
        }
        */


        //--------------------------------Esto es sin clean arquitecture----------------------
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<DogResponse>> callListDogResponse=iDogApiService.getDataDogBreed(breedId);
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    List<DogResponse> listDogResponse=response.body();
                    if(listDogResponse.size()>0){
                        DogResponse dogResponse=listDogResponse.get(0);
                        mutableLiveDataDogresponse.postValue(dogResponse);
                        mutableLiveDataProgressBarVisible.postValue(false);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {

            }
        });


    }



}
