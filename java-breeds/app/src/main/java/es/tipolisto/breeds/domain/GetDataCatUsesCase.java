package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;

public class GetDataCatUsesCase {
    DataRepository dataRepository;
    public GetDataCatUsesCase(){
        dataRepository=new DataRepository();
    }

    public Cat getDataCat(String breedId){
        return dataRepository.getDataCat(breedId);
    }
}
