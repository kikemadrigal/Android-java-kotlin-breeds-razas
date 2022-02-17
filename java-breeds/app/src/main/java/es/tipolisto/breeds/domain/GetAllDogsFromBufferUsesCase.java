package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.DogResponse;

public class GetAllDogsFromBufferUsesCase {
    private DataRepository dataRepository;
    public GetAllDogsFromBufferUsesCase(){
        dataRepository=new DataRepository();
    }


    public List<DogResponse> getAllDogsFromBuffer(){
        return dataRepository.getListDogsFromBuffer();
    }
}
