package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.CatListResponse;

public class GetCatsUsesCase {
    DataRepository dataRepository;
    public GetCatsUsesCase(){
        dataRepository=new DataRepository();
    }

    public List<CatListResponse> getCatList(){
        return dataRepository.getListCatInternet();
    }
}
