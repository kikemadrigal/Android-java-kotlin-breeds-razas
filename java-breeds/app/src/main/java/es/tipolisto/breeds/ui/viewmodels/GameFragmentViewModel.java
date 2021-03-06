package es.tipolisto.breeds.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSourceProvider;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.network.RetrofitClient;
import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.domain.GetCatUsesCase;
import es.tipolisto.breeds.domain.GetDogUsesCase;

public class GameFragmentViewModel extends ViewModel {

    private MutableLiveData<CatSimple> mutableSimpleCat;
    private MutableLiveData<Cat> mutableCat;
    private MutableLiveData<Dog> mutableDog;
    private MutableLiveData<Boolean> mutableProgressBarVisible;
    private String breedNameCat,breednameDog;
    private String urlRestore;
    private Dog dog;
    private String[] textRadioButtons;

    private GetCatUsesCase getCatUsesCase;
    private GetDogUsesCase getDogUsesCase;

    private RetrofitClient retrofitClient;
    public GameFragmentViewModel(){
        retrofitClient=new RetrofitClient();
        mutableSimpleCat=new MutableLiveData<CatSimple>();
        mutableCat=new MutableLiveData<Cat>();
        mutableDog=new MutableLiveData<Dog>();
        mutableProgressBarVisible=new MutableLiveData<Boolean>();
        getCatUsesCase=new GetCatUsesCase();
        getDogUsesCase=new GetDogUsesCase();
    }

    /**************************************************
    *********************Getters & Setters ************
     **************************************************/
    public MutableLiveData<CatSimple> getMutableSimpleCat() {
        return mutableSimpleCat;
    }
    public MutableLiveData<Cat> getMutableCat() {
        return mutableCat;
    }
    public MutableLiveData<Dog> getMutableDog() {
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
    public void setTextRadioButtons(String[] textRadioButtons) { this.textRadioButtons = textRadioButtons;}
    //Nos permite recuprar la foto almacenada cuando volvemos al fragment
    public String getUrlRestore() {
        return urlRestore;
    }
    public void setUrlRestore(String urlRestore) {
        this.urlRestore = urlRestore;
    }
    //Nos permite mostrar una barra de progreso en el fragment
    public MutableLiveData<Boolean> getMutableProgressBarVisible() { return mutableProgressBarVisible;}
    /**************************************************
     ******************End Getters & Setters **********
     **************************************************/





    public boolean checkCatsEquals(Cat[] cats){
        boolean equals=false;
        String idCat0= cats[0].getId();
        String idCat1= cats[1].getId();
        String idCat2= cats[2].getId();
        //le hacemos repetir hasta que consigamos 2 diferentes
        if(idCat0.equals(idCat1)) equals=true;
        else if (idCat0.equals(idCat2)) equals=true;
        else if (idCat1.equals(idCat2)) equals=true;
        return equals;
    }

    public Cat[] get3RamdomCats(){
        Cat[] cats=new Cat[3];
        cats[0]=getCatUsesCase.getRandomCatFromBuffer();
        cats[1]=getCatUsesCase.getRandomCatFromBuffer();
        cats[2]=getCatUsesCase.getRandomCatFromBuffer();

        return cats;
    }


    public boolean checkDogsEquals(Dog[] dogs){
        boolean equals=false;
        //Comprobamos que son perros distintos
        String name0= dogs[0].getBreeds().get(0).getName();
        String name1= dogs[1].getBreeds().get(0).getName();
        String name2= dogs[2].getBreeds().get(0).getName();
        Log.d("Mensaje","0: "+name0+", 1: "+name1+", 2: "+name2);
        //le hacemos repetir hasta que consigamos 2 diferentes
        if(name0.equals(name1)) equals=true;
        if (name0.equals(name2)) equals=true;
        if (name1.equals(name2)) equals=true;
        return equals;
    }

    public Dog[] get3RamdomDogs(){
        Dog[] dogs=new Dog[3];
        dogs[0]=dog;
        dogs[1]=getDogUsesCase.getRandomDogFromBuffer();
        dogs[2]=getDogUsesCase.getRandomDogFromBuffer();
        return dogs;
    }

    public void updatePhotoCat(String breedId){
        mutableProgressBarVisible.postValue(true);
        Cat catFind=null;
        //Obtenemos la lista de gatos ya precargada
        List<Cat> listCats=ArrayDataSourceProvider.listAllcats;
        //Dame el que tenga esta raza
        for (Cat cat:listCats){
            if(cat.getId().equals(breedId)){
                catFind=cat;
                break;
            }
        }
        //Cat cat =getCatUsesCase.getCatFromBuffer(breedId);
        if(catFind!=null){
            mutableCat.postValue(catFind);
            mutableProgressBarVisible.postValue(false);
        }

    }

    public void updatePhotoDog(){
        mutableProgressBarVisible.postValue(true);
        dog=getDogUsesCase.getRandomDogFromBuffer();
        if (dog.getBreeds().size()>0) {
            BreedsDog breedsDog = dog.getBreeds().get(0);
            breednameDog = breedsDog.getName();
            urlRestore = dog.getUrl();
            mutableDog.postValue(dog);
            mutableProgressBarVisible.postValue(false);
        }else{
            updatePhotoDog();
        }

    }
}
