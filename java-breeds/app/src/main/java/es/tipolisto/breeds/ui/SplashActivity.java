package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSourceProvider;
import es.tipolisto.breeds.data.database.AppDatabase;
import es.tipolisto.breeds.data.database.RecordDao;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.data.model.RecordEntity;
import es.tipolisto.breeds.domain.GetCatsUsesCase;
import es.tipolisto.breeds.domain.GetDogsUsesCase;
import es.tipolisto.breeds.domain.GetRecordsUsesCase;
import es.tipolisto.breeds.ui.dialogs.Dialog;
import es.tipolisto.breeds.utils.Util;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Util.isNetworkConnected(getApplicationContext())) Dialog.showDialogNecessaryInternet(this);
        storeInMemoryTheListsOfDogsAndCats();
        checkRecordList();
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    private void storeInMemoryTheListsOfDogsAndCats() {
        GetCatsUsesCase getCatsUsesCase=new GetCatsUsesCase();
        List<Cat> listCatListResponse=getCatsUsesCase.getCatListFromInternet();
        if(listCatListResponse==null) Dialog.showDialogProblemGetDataInternet(this);
        GetDogsUsesCase getDogsUsesCase=new GetDogsUsesCase();
        List<Dog> listDogResponse=getDogsUsesCase.getAllDogsFromInternet();
        if (listDogResponse==null) Dialog.showDialogProblemGetDataInternet(this);
    }

    private void checkRecordList(){
        /*
        GetRecordsUsesCase getRecordsUsesCase=new GetRecordsUsesCase();
        List<RecordEntity> listScores=getRecordsUsesCase.getLast20Records();
        */
        //Si la lista de records está vacía creamos una
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        RecordDao recordDao= db.recordDao();
        List<RecordEntity> listScores=recordDao.getAllRecordEntities();

        //Si no hay records, creamos los records dentro de la tabla a apartir de un hasmap guardado en código
        if(listScores.size()==0){
            HashMap<Integer, String> hashMapRecordList= ArrayDataSourceProvider.getMaprecordList();
            String value;
            Iterator<Integer> iterator=hashMapRecordList.keySet().iterator();
            while(iterator.hasNext()){
                Integer llave=(Integer) iterator.next();
                value =hashMapRecordList.get(llave);
                RecordEntity userEntity=new RecordEntity();
                userEntity.setName(value);
                userEntity.setScore(llave);
                recordDao.insertAll(userEntity);
            }
            Dialog.showSialogPresentation(this);
        }
    }
}