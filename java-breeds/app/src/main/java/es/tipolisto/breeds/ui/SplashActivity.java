package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.tipolisto.breeds.data.DataRepository;
import es.tipolisto.breeds.data.buffer.ArrayDataSource;
import es.tipolisto.breeds.data.database.AppDatabase;
import es.tipolisto.breeds.data.database.RecordDao;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.model.DogResponse;
import es.tipolisto.breeds.data.model.RecordEntity;
import es.tipolisto.breeds.domain.GetCatsUsesCase;
import es.tipolisto.breeds.domain.GetDogsUsesCase;
import es.tipolisto.breeds.ui.dialogs.Dialog;
import es.tipolisto.breeds.utils.Util;

public class SplashActivity extends AppCompatActivity {

    private List<RecordEntity> listUserScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.isNetworkConnected(this);
        almacenarEnMemoriaLasListasDePerrosYGatos();
        //Si la lista de records está vacía creamos una
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        RecordDao recordDao= db.recordDao();
        List<RecordEntity> listUserScores=recordDao.getAllRecordEntities();
        //Si no hay records, creamos los records dentro de la tabla a apartir de un hasmap guardado en código
        if(listUserScores.size()==0){
            HashMap<Integer, String> hashMapRecordList= ArrayDataSource.getMaprecordList();
            String value="";
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
        }else{
            Intent intent=new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void almacenarEnMemoriaLasListasDePerrosYGatos() {
        GetCatsUsesCase getCatsUsesCase=new GetCatsUsesCase();
        List<CatListResponse> listCatListResponse=getCatsUsesCase.getCatList();
        if(listCatListResponse!=null)
            Log.d("Mensaje","Error al descargar de internet");
        GetDogsUsesCase getDogsUsesCase=new GetDogsUsesCase();
        List<DogResponse> listDogResponse=getDogsUsesCase.getAllDogs();
        if (listDogResponse!=null)
            Log.d("Mensaje","Error al descargar de internet");
    }
}