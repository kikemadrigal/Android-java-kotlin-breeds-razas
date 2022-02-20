package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Dog;

public class GetDogUsesCase {
    private final DataRepository dataRepository;

    public GetDogUsesCase(){
        dataRepository=new DataRepository();
    }


    public Dog getRandomDogFromBuffer(){
        return dataRepository.getDogRandomFromBuffer();
    }

    public Dog getDogByNameFromBuffer(String name){
        return dataRepository.getDogByBreedNameFromBuffer(name);
    }

    public Dog getDogByBreedFromInternet(){
        return dataRepository.getDogByBreedFromInternet();
    }
}
