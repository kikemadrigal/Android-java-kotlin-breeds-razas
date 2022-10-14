package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Dog;

public class GetDogUsesCase {
    private final DataRepository dataRepository;

    public GetDogUsesCase(){
        dataRepository=new DataRepository();
    }


    public Dog getRandomDogFromBuffer(){
        return dataRepository.getDogRandomFromBuffer();
    }
    public BreedsDog getRandomBreedsDogFromBuffer(){
        return dataRepository.getBreedsDogRandomFromBuffer();
    }
    public BreedsDog getDogByNameFromBuffer(String name){
        return dataRepository.getDogByBreedNameFromBuffer(name);
    }
     public List<Dog> getListDogsFromBuffer(){
        return dataRepository.getListDogsFromBuffer();
     }
    public Dog getDogByBreedFromInternet(){
        return dataRepository.getDogByBreedFromInternet();
    }
}
