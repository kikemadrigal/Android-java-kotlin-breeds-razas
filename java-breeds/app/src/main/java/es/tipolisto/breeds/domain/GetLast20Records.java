package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.RecordEntity;

public class GetLast20Records {
    private DataRepository dataRepository;
    public GetLast20Records(){
        dataRepository=new DataRepository();
    }

    public List<RecordEntity> getLast20Records(){
        return null;
    }
}
