package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.DogResponse;

public class GetDogsUsesCase {
    private DataRepository dataRepository;
    public GetDogsUsesCase(){
        dataRepository=new DataRepository();
    }
    public List<DogResponse> getAllDogs(){
        return dataRepository.getListDogInternet();
    }
}
