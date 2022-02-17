package es.tipolisto.breeds.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.network.ICatsApi;
import es.tipolisto.breeds.data.network.IDogApi;
import es.tipolisto.breeds.data.network.RetrofitClient;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.DogResponse;
import es.tipolisto.breeds.domain.GetCatByBreedUsesCase;
import es.tipolisto.breeds.domain.GetDogByBreedUsesCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameFragmentViewModel extends ViewModel {

    private MutableLiveData<Cat> mutableCat;
    private MutableLiveData<DogResponse> mutableDog;
    private MutableLiveData<Boolean> mutableProgressBarVisible;
    private String breedNameCat,breednameDog;
    private String urlRestore;
    private String[] textRadioButtons;

    private GetCatByBreedUsesCase getCatByBreedUsesCase;

    private RetrofitClient retrofitClient;
    public GameFragmentViewModel(){
        retrofitClient=new RetrofitClient();
        mutableCat=new MutableLiveData<Cat>();
        mutableDog=new MutableLiveData<DogResponse>();
        mutableProgressBarVisible=new MutableLiveData<Boolean>();

        getCatByBreedUsesCase=new GetCatByBreedUsesCase();
    }


    public MutableLiveData<Cat> getMutableCat() {
        return mutableCat;
    }
    public MutableLiveData<DogResponse> getMutableDog() {
        return mutableDog;
    }

    //BreednameCat nos permite comparalo con el que ha hecho click de los radio buttons
    public String getBreedNameCat() {
        return breedNameCat;
    }
    public void setBreedNameCat(String breedNameCat) {
        this.breedNameCat = breedNameCat;
    }
    public String getBreednameDog() {
        return breednameDog;
    }
    public void setBreednameDog(String breednameDog) {
        this.breednameDog = breednameDog;
    }

    public String[] getTextRadioButtons() {
        return textRadioButtons;
    }

    public void setTextRadioButtons(String[] textRadioButtons) {
        this.textRadioButtons = textRadioButtons;
    }
    //Nos permite recuprar la foto almacenada cuando volvemos al fragment
    public String getUrlRestore() {
        return urlRestore;
    }
    public void setUrlRestore(String urlRestore) {
        this.urlRestore = urlRestore;
    }

    //Nos permite mostrar una barra de progreso en el fragment
    public MutableLiveData<Boolean> getMutableProgressBarVisible() {
        return mutableProgressBarVisible;
    }



    public String[] dameUnIdYNombreRazaDe3Aleatorios(String mode){
        String[] cat=new String[6];
        HashMap<String, String> hashMap=new HashMap<>();
        //Conseguimos 3 números aleatorios
        if(mode.equals("cat"))
            hashMap= ArrayDataSource.getMapCatIdNames();
        else if(mode.equals("dog")){
            hashMap= ArrayDataSource.getMapDogIdNames();
        }
        int numeroAleatorio1=(int) (Math.random() * hashMap.size()-1) + 1;
        //Ibtenemos otro id y raza y comprobamos si está repe
        int numeroAleatorio2=(int) (Math.random() * hashMap.size()-1) + 1;
        //Mientras que el número aletario 2 sea igual que el 1 pediremos otro numero
        while(numeroAleatorio1==numeroAleatorio2) {
            numeroAleatorio2=(int) (Math.random() * hashMap.size()-1) + 1;
        }
        int numeroAleatorio3=(int) (Math.random() * hashMap.size()-1) + 1;
        while(numeroAleatorio1==numeroAleatorio3 || numeroAleatorio2==numeroAleatorio3) {
            numeroAleatorio3=(int) (Math.random() * hashMap.size()-1) + 1;
        }
        //Recorremos el hasmap y obtenemos el id y raza aletatoria
        int contador=0;
        Iterator<String> iterator=hashMap.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String value = hashMap.get(key);
            if(contador==numeroAleatorio1){
               cat[0]=key;
               cat[1]=value;
            }else if(contador==numeroAleatorio2){
                cat[2]=key;
                cat[3]=value;
            }else if(contador==numeroAleatorio3){
                cat[4]=key;
                cat[5]=value;
            }
            contador++;
        }
        return cat;
    }



    public void updatePhotoCat(String breedId){
        mutableProgressBarVisible.postValue(true);
        /*Cat cat =getCatByBreedUsesCase.getCatByBreed(breedId);
        if(cat!=null){
            mutableCat.postValue(cat);
            mutableProgressBarVisible.postValue(false);
        }*/


        //-----------------------Esto es sin clean arquitecture---------------------------
        //Obtenemos la imagen
        ICatsApi iCatsApiService= retrofitClient.getCatApiService();
        //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
        Call<List<Cat>> callCat=iCatsApiService.getcatById(breedId);
        callCat.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                   List<Cat>listCat=response.body();
                   Cat cat=listCat.get(0);
                   mutableCat.postValue(cat);
                   mutableProgressBarVisible.postValue(false);
                }
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Log.d("Mensaje","Mal");
                Cat cat=new Cat(null, "0","No se obtuvo","0","0");
                mutableCat.postValue(cat);
                mutableProgressBarVisible.postValue(false);
            }
        });

    }



    public void updatePhotoDog(){
        mutableProgressBarVisible.postValue(true);
        /*GetDogByBreedUsesCase getDogByBreedUsesCase=new GetDogByBreedUsesCase();
        DogResponse dogResponse=getDogByBreedUsesCase.getDogByBreed();
        if (dogResponse!=null){
            BreedsDog breedsDog=dogResponse.getBreeds().get(0);
            if (breedsDog==null){
                breednameDog="null";
            }else{
                breednameDog=breedsDog.getName();
            }
            urlRestore=dogResponse.getUrl();
            Log.d("Mensaje", "Dog respones "+breednameDog);
            mutableDog.postValue(dogResponse);
            mutableProgressBarVisible.postValue(false);
        }*/



        //-----------------------Esto es sin clean arquitecture---------------------------
        IDogApi iDogApiService=retrofitClient.getDogApiService();
        Call<List<DogResponse>> callListDogResponse=iDogApiService.getDog();
        callListDogResponse.enqueue(new Callback<List<DogResponse>>() {
            @Override
            public void onResponse(Call<List<DogResponse>> call, Response<List<DogResponse>> response) {
                if(response.isSuccessful()){
                    List<DogResponse> listDogResponse=response.body();
                    Log.d("Mensaje", "Tamaño lista "+listDogResponse.size());
                    if (listDogResponse.size()>0){
                        DogResponse dogResponse=listDogResponse.get(0);
                        if (dogResponse.getBreeds().size()>0){
                            BreedsDog breedsDog=dogResponse.getBreeds().get(0);
                            breednameDog=breedsDog.getName();

                            urlRestore=dogResponse.getUrl();
                            Log.d("Mensaje", "Dog respones "+breednameDog);
                            mutableDog.postValue(dogResponse);
                            mutableProgressBarVisible.postValue(false);
                        }else{
                            updatePhotoDog();
                        }
                    }else{
                        updatePhotoDog();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<DogResponse>> call, Throwable t) {
                Log.d("Mensaje","Hubo un fallo");
                mutableProgressBarVisible.postValue(false);
            }
        });
    }
}
