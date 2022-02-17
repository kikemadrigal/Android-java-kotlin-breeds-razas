package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.model.DogResponse;

public class GetAllCatFromBufferUsesCase {
    private DataRepository dataRepository;
    public GetAllCatFromBufferUsesCase(){
        dataRepository=new DataRepository();
    }


    public List<CatListResponse> getAllCatsFromBuffer(){
        return dataRepository.getListCatFromBuffer();
    }
}
