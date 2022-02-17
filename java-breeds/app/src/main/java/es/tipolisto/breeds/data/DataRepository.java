package es.tipolisto.breeds.data;

import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.network.CatService;
import es.tipolisto.breeds.data.network.DogService;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.model.DogResponse;

public class DataRepository {
   private CatService catService;
   private DogService dogService;
    public DataRepository() {
        catService=new CatService();
        dogService=new DogService();
    }

    //Pantalla de los radioButtons
    public Cat getCatByBreed(String breedId){
        return catService.getCatByBreed(breedId);
    }
    public DogResponse getDogByBreed(){
        return dogService.getDogByBreed();
    }

    //Pantalla de los recyclerviews
    public List<CatListResponse> getListCatInternet(){
        return catService.getAllCats();
    }

    public List<CatListResponse> getListCatFromBuffer(){
        return ArrayDataSource.listAllcats;
    }
    public List<DogResponse> getListDogInternet(){
        return dogService.getAllDogs();
    }
    public List<DogResponse> getListDogsFromBuffer(){
        return ArrayDataSource.listAllDogs;
    }


    //Pantalla de los datos
    public Cat getDataCat(String breedId){
        return catService.getdataBreedCat(breedId);
    }
    public DogResponse getDataDog(String breedId){
        return dogService.getDataDog(breedId);
    }




}
