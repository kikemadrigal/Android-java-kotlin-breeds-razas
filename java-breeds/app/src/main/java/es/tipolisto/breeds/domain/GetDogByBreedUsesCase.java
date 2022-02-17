package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.DogResponse;

public class GetDogByBreedUsesCase {
    private DataRepository dataRepository;
    public GetDogByBreedUsesCase(){
        dataRepository=new DataRepository();
    }

    public DogResponse getDogByBreed(){
        return dataRepository.getDogByBreed();
    }
}
