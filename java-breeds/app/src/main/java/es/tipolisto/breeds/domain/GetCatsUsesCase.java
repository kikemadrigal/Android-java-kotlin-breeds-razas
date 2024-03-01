package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;

public class GetCatsUsesCase {
    private final DataRepository dataRepository;
    public GetCatsUsesCase(){
        dataRepository=new DataRepository();
    }

    public void getCatListFromInternetAndInsertOnBuffer(){
        dataRepository.getListCatInternetAndInsertOnBuffer();
    }
    public List<Cat> getAllCatsFromBuffer(){
        return dataRepository.getListCatFromBuffer();
    }
}
