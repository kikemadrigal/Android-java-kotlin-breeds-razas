package es.tipolisto.breeds.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSourceProvider;
import es.tipolisto.breeds.data.database.AppDatabase;
import es.tipolisto.breeds.data.database.RecordDao;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.RecordEntity;
import es.tipolisto.breeds.data.network.CatService;
import es.tipolisto.breeds.data.network.DogService;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.data.network.IDogApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
   private CatService catService;
   private DogService dogService;

    public DataRepository() {
        catService=new CatService();
        dogService=new DogService();
    }

    /***
     *      CATS
     */
    //Pantalla de los radioButtons
    public CatSimple getCatByBreedInternet(String breedId){
        return catService.getCatByBreedInternet(breedId);
    }
    public Cat getCatByBreedIdFromBuffer(String breedID){
        Cat catFind=null;
        //Obtenemos la lista de gatos ya precargada
        List<Cat> listCats=ArrayDataSourceProvider.listAllcats;
        //Dame el que tenga esta raza
        for (Cat cat:listCats){
            if(cat.getId().equals(breedID))
                catFind=cat;
            else
                catFind=null;
        }
        return catFind;
    }
    public Cat getCatByBreedNameFromBuffer(String name){
        Cat catFind=null;
        //Obtenemos la lista de gatos ya precargada
        List<Cat> listCats=ArrayDataSourceProvider.listAllcats;
        //Dame el que tenga esta raza
        for (Cat cat:listCats){
            if(cat.getName().equals(name)){
                catFind=cat;
                break;
            }
        }
        return catFind;
    }



    public Cat getCatRandomFromBuffer(){
        //Obtenemos la lista de gatos ya precargada
        List<Cat> listCats=ArrayDataSourceProvider.listAllcats;
        int ramdonNumber= (int) (Math.random() * listCats.size()-1);
        return listCats.get(ramdonNumber);
    }















    /***
     *      DOGS
     */

    public Dog getDogRandomFromBuffer(){
        //Obtenemos la lista de gatos ya precargada
        List<Dog> listDogs=getListDogsFromBuffer();
        //Log.d("Mensaje","Hay en el buffer: "+listDogs.size());
        Dog dog=null;
        if(listDogs.size()!=0){
            int ramdonNumber= (int) (Math.random() * listDogs.size()-1);
            dog=listDogs.get(ramdonNumber);
        }else{
            Log.d("Mensaje","No hay perros en la lista");
        }
        return dog;
    }
    public BreedsDog getBreedsDogRandomFromBuffer(){
        //Obtenemos la lista de gatos ya precargada
        List<BreedsDog> listDogs=getListBreedsDogsFromBuffer();
        //Log.d("Mensaje","Hay en el buffer: "+listDogs.size());
        BreedsDog breedsDog=null;
        if(listDogs.size()!=0){
            int ramdonNumber= (int) (Math.random() * listDogs.size()-1);
            breedsDog=listDogs.get(ramdonNumber);
        }else{
            Log.d("Mensaje","No hay perros en la lista");
        }
        return breedsDog;
    }

    public Dog getDogByBreedFromInternet(){
        return dogService.getDogByBreed();
    }

    //Pantalla de los recyclerviews
    public List<Cat> getListCatInternet(){
        return catService.getAllCats();
    }

    public List<Cat> getListCatFromBuffer(){
        return ArrayDataSourceProvider.listAllcats;
    }
    public List<Dog> getListDogInternet(){
        return dogService.getAllDogs();
    }
    public List<BreedsDog> getListBreedDogInternet(){
        return dogService.getAllBreedDogs();
    }
    public List<Dog> getListDogsFromBuffer(){
        //Quitamos todos los perros que no tienen raza
        deleteEmptyBreedDogFromBuffer();
        deleteEmptyBreedDogFromBuffer();
        deleteEmptyBreedDogFromBuffer();
        return ArrayDataSourceProvider.listAllDogs;
    }
    public List<BreedsDog> getListBreedsDogsFromBuffer(){
        return ArrayDataSourceProvider.listAllBreedDogs;
    }



    public void deleteEmptyBreedDogFromBuffer(){
        List<Dog> listDog=ArrayDataSourceProvider.listAllDogs;
        //Log.d("Mensaje", "Tamaño lista de peros "+String.valueOf(listDog.size()));
        for (int i=0;i<listDog.size();i++){
            Dog dog=listDog.get(i);
            /*List<BreedsDog> listBreedsDog=dog.getBreeds();
            if (listBreedsDog.size()==0){
                //Log.d("Mensaje", "Encontrado vacío el "+String.valueOf(i));
                listDog.remove(i);
            }*/
        }
        //Log.d("Mensaje", "Tamaño lista de peros despues de borrar "+String.valueOf(listDog.size()));
        ArrayDataSourceProvider.listAllDogs=listDog;
    }


    //Pantalla de los datos
    public CatSimple getDataSimpleCatFromInternet(String breedId){
        return catService.getdataBreedSimpleCatFromInternet(breedId);
    }

    public Cat getCatFromBuffer(String breedId){
        Cat cat=null;
        List<Cat> listCat=ArrayDataSourceProvider.listAllcats;
        for(int i=0;i<listCat.size()-1;i++){
            Cat catFind=listCat.get(i);
            if(catFind.getId().equals(breedId)){
                cat=catFind;
                break;
            }
        }
        return cat;
    }
    public String generateIdCatFromBuffer(String nameBreedCat){
        String idBreedCat="";
        List<Cat> listCat=ArrayDataSourceProvider.listAllcats;
        for(int i=0 ; i<listCat.size()-1;i++){
            Cat catFind=listCat.get(i);
            if(catFind.getName().equals(nameBreedCat)){
                idBreedCat= catFind.getId();
                break;
            }
        }
        return idBreedCat;
    }



    public Dog getDataDogFromInternet(String breedId){
        return dogService.getDataDog(breedId);
    }

    public BreedsDog getDogByBreedNameFromBuffer(String name){
        BreedsDog dogFind=null;
        //Obtenemos la lista de gatos ya precargada
        List<BreedsDog> listDogs=ArrayDataSourceProvider.listAllBreedDogs;
        //Dame el que tenga esta raza
        for (BreedsDog dog:listDogs){
            if(dog.getName().equals(name)){
                dogFind=dog;
                break;
            }
        }
        return dogFind;
    }

    public List<RecordEntity> getrecordsFromdatbase(){
        return null;
    }
}
