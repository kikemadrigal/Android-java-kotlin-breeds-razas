package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.DogResponse;

public class GetDataDogUsesCase {
    DataRepository dataRepository;
    public GetDataDogUsesCase(){
        dataRepository=new DataRepository();
    }

    public DogResponse getDataDog(String breedId){
        return dataRepository.getDataDog(breedId);
    }
}
