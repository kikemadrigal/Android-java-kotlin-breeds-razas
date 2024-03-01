package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Dog;

public class GetDogsUsesCase {
    private final DataRepository dataRepository;
    public GetDogsUsesCase(){
        dataRepository=new DataRepository();
    }
    public List<Dog> getAllDogsFromInternet(){
        return dataRepository.getListDogInternet();
    }
    public void getAllBreedDogsFromInternetAndIsertOnBuffer(){
        dataRepository.getListBreedDogInternet();
    }
    public List<Dog> getAllDogsFromBuffer(){
        return dataRepository.getListDogsFromBuffer();
    }
    public List<BreedsDog> getAllBreedsDogsFromBuffer(){
        return dataRepository.getListBreedsDogsFromBuffer();
    }
}
