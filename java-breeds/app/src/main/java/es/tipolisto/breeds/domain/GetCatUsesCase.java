package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.CatSimple;

public class GetCatUsesCase {
    private final DataRepository dataRepository;
    public GetCatUsesCase(){
        dataRepository=new DataRepository();
    }

    public CatSimple getCatByBreedFromInternet(String breedId){
       return dataRepository.getCatByBreedInternet(breedId);
    }
    public Cat getCatByIdFromBuffer(String breedId){
        return dataRepository.getCatByBreedIdFromBuffer(breedId);
    }
    public Cat getCatByNameFromBuffer(String name){
        return dataRepository.getCatByBreedNameFromBuffer(name);
    }


    public Cat getRandomCatFromBuffer(){
        return dataRepository.getCatRandomFromBuffer();
    }

    public String generateIdCat(String breedId){
        return dataRepository.generateIdCatFromBuffer(breedId);
    }
}
