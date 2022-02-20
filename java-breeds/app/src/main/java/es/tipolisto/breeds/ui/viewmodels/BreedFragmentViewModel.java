package es.tipolisto.breeds.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.domain.GetCatUsesCase;
import es.tipolisto.breeds.domain.GetDogUsesCase;

public class BreedFragmentViewModel extends ViewModel {

    private final MutableLiveData<Cat> mutableLiveDataCat;
    private final MutableLiveData<Dog> mutableLiveDataDog;
    private final MutableLiveData<Boolean> mutableLiveDataProgressBarVisible;
    private final GetCatUsesCase getCatUsesCase;
    private final GetDogUsesCase getDogUsesCase;
    public  BreedFragmentViewModel() {
        mutableLiveDataCat=new MutableLiveData<>();
        mutableLiveDataProgressBarVisible=new MutableLiveData<>();
        mutableLiveDataDog=new MutableLiveData<>();
        getCatUsesCase=new GetCatUsesCase();
        getDogUsesCase=new GetDogUsesCase();
    }

    public MutableLiveData<Cat> getMutableLiveDataCat() {
        return mutableLiveDataCat;

    }

    public MutableLiveData<Dog> getMutableLiveDataDog() {
        return mutableLiveDataDog;
    }

    public MutableLiveData<Boolean> getMutableLiveDataProgressBarVisible() {
        return mutableLiveDataProgressBarVisible;
    }

    public String generateIdCat(String nameBreedCat){
       return getCatUsesCase.generateIdCat(nameBreedCat);
    }


    public void getCatByIdFromBuffer(String breedId){
        mutableLiveDataProgressBarVisible.postValue(true);
        Cat cat=getCatUsesCase.getCatByIdFromBuffer(breedId);
        if(cat!=null){
            mutableLiveDataCat.postValue(cat);
            mutableLiveDataProgressBarVisible.postValue(false);
        }

    }
    public void getCatBynameFromBuffer(String name){
        mutableLiveDataProgressBarVisible.postValue(true);
        Cat cat=getCatUsesCase.getCatByNameFromBuffer(name);
        if(cat!=null){
            mutableLiveDataCat.postValue(cat);
            mutableLiveDataProgressBarVisible.postValue(false);
        }
    }


    public void getDogBynameFromBuffer(String name){
        mutableLiveDataProgressBarVisible.postValue(true);
        Dog dog =getDogUsesCase.getDogByNameFromBuffer(name);
        if(dog!=null){
            mutableLiveDataDog.postValue(dog);
            mutableLiveDataProgressBarVisible.postValue(false);
        }
    }



}
