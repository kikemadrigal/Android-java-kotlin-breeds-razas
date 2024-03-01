package es.tipolisto.breeds.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.domain.GetCatsUsesCase;
import es.tipolisto.breeds.domain.GetDogsUsesCase;
import es.tipolisto.breeds.utils.Constants;


public class AnimalListFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Cat>> mutableCatListResponse;
    private MutableLiveData<List<BreedsDog>> mutableListBreedsDogResponse;
    private MutableLiveData<Boolean> mutableProgressBarVisible;
    private int positinRecyclerView;
    //El repositorio llama al service, el service al api y almacena la respuesta del api en un list
    private GetCatsUsesCase getCatsUsesCase;
    private GetDogsUsesCase getDogsUsesCase;

    public AnimalListFragmentViewModel(){
        mutableCatListResponse=new MutableLiveData<List<Cat>>();
        mutableListBreedsDogResponse=new MutableLiveData<List<BreedsDog>>();
        mutableProgressBarVisible=new MutableLiveData<Boolean>();
        getCatsUsesCase=new GetCatsUsesCase();
        getDogsUsesCase=new GetDogsUsesCase();
        positinRecyclerView=0;
    }

    public MutableLiveData<List<Cat>> getMutableCatListResponse() {
        return mutableCatListResponse;
    }
    public MutableLiveData<List<BreedsDog>> getMutableListBreedsDogResponse() {
        return mutableListBreedsDogResponse;
    }
    public MutableLiveData<Boolean> getMutableProgressBarVisible() { return mutableProgressBarVisible;}


    public int getPositinRecyclerView() {
        return positinRecyclerView;
    }

    public void setPositinRecyclerView(int positinRecyclerView) {
        this.positinRecyclerView = positinRecyclerView;
    }

    public void getListCat(){
        mutableProgressBarVisible.postValue(true);
        List<Cat> listCatListResponse=getCatsUsesCase.getAllCatsFromBuffer();
        if(listCatListResponse!=null){
            mutableCatListResponse.postValue(listCatListResponse);
            mutableProgressBarVisible.postValue(false);
        }


        /*
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        //List<CatListResponse> listCatListREsponse=dataRepository.getListCatInternet();
        Call<List<CatListResponse>> callCatListResponse=iCatsApiService.getAllCats();
        callCatListResponse.enqueue(new Callback<List<CatListResponse>>() {
            @Override
            public void onResponse(Call<List<CatListResponse>> call, Response<List<CatListResponse>> response) {
                if(response.isSuccessful()){
                    List<CatListResponse> listCats=response.body();
                    //Log.d("Mensaje", "recibidos: "+listCats.size());
                    mutableCatListResponse.postValue(listCats);
                }
            }
            @Override
            public void onFailure(Call<List<CatListResponse>> call, Throwable t) {

            }
        });
        */
    }


    public void getListDog(){
        mutableProgressBarVisible.postValue(true);
        List<BreedsDog> listBreedsDogResponse=getDogsUsesCase.getAllBreedsDogsFromBuffer();
        Log.d(Constants.LOG,"tamaño lista de perros: "+listBreedsDogResponse.size());
        if (listBreedsDogResponse!=null){
            mutableListBreedsDogResponse.postValue(listBreedsDogResponse);
            mutableProgressBarVisible.postValue(false);
        }


        /*
        //List<DogResponse> listDogResponse=dataRepository.getListDogInternet();
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call <List<DogResponse>> callListDogResponse=iDogApiService.getListDog();
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    List<DogResponse> listDogResponse=response.body();
                    mutableListDogResponse.postValue(listDogResponse);
                    //Log.d("Mensaje", "tamaño lista perros "+listDogResponse.size());
                }
            }

            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {

            }
        });
         */
    }

}
