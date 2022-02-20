package es.tipolisto.breeds.domain;

import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.RecordEntity;

public class GetRecordsUsesCase {
    private final DataRepository dataRepository;
    public GetRecordsUsesCase(){
        dataRepository=new DataRepository();
    }

    public List<RecordEntity> getLast20Records(){
        return dataRepository.getrecordsFromdatbase();
    }
}
