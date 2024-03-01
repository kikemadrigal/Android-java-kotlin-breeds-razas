package es.tipolisto.breeds.domain;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.model.Cat;

public class SetImageCatUsesCase {
    private final DataRepository dataRepository;
    public SetImageCatUsesCase(){
        dataRepository=new DataRepository();
    }

    public void setImageCatFromInternetByreferenceImageId(){
        dataRepository.setImageCatFromInternetByreferenceImageId();
    }
}
