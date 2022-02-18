package es.tipolisto.breeds.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.ui.adapters.RecordListViewAdapter;
import es.tipolisto.breeds.data.database.AppDatabase;
import es.tipolisto.breeds.data.database.RecordDao;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.databinding.FragmentRecordsBinding;
import es.tipolisto.breeds.data.model.RecordEntity;


public class RecordsFragment extends Fragment {
    private FragmentRecordsBinding binding=null;





    public RecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View view=inflater.inflate(R.layout.fragment_records, container, false);
        binding= FragmentRecordsBinding.inflate(inflater, container, false);

        PreferencesManagaer preferencesManagaer=new PreferencesManagaer(getContext());
        int score=preferencesManagaer.getHighRecord();
        String name=preferencesManagaer.getnameRecord();
        binding.textViewSettingsRecords.setText(name+": "+String.valueOf(score));
        //todo:comprobar si su nombre tiene que estar en la tabla de records
        //Aignamos el comportamiento a los botones
        binding.buttonSettingsDeleteRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesManagaer.deletePreferences();
                binding.textViewSettingsRecords.setText(String.valueOf(preferencesManagaer.getHighRecord()));
            }
        });
        binding.buttonSubmitInternetRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), R.string.Payment_method_only, Toast.LENGTH_SHORT).show();
            }
        });

        AppDatabase db = Room.databaseBuilder(getContext(),AppDatabase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        RecordDao recordDao= db.recordDao();
        //Obtenemos la lista con las 10 puntuaciones más altas
        //List<RecordEntity> listRecordsEntity=recordDao.getAllRecordEntities();
        List<RecordEntity> listRecordsEntity=recordDao.getLast10RecordEntities();
        //Esto lo hacemos para que no vuelva a incluir el mismo nombre cuando entre en la actividad otra vez
        RecordEntity existName=recordDao.getNameRecord(name);
        //Si tienes una puntuación más alta que alguno de estos se incluye en la base de datos
        for (RecordEntity recordEntity: listRecordsEntity){
            if (existName==null){
                if(recordEntity.getScore()<score){
                    RecordEntity userEntity=new RecordEntity();
                    userEntity.setName(name);
                    userEntity.setScore(score);
                    recordDao.insertAll(userEntity);
                    //El break es para que solo cree un usaurio, si lo kitas crearía todos los que tenga una punutción inferior
                    break;
                }
            }
        }
        //Obtener puntución más baja
        Log.d("Mensaje", "la puntuación mas baja es "+listRecordsEntity.get(8).getScore()+" id "+listRecordsEntity.get(8).getId()+ "name "+listRecordsEntity.get(8).getName());
        //Borramos todos los registros en la base de datos cuya puntuación se menor que la mas baja de la tabla
        int deletes=recordDao.deleteAllMinor(listRecordsEntity.get(8).getScore());
        Log.d("Mensaje", "borrado "+String.valueOf(deletes));
        //Volvemos a pedir la lista con la pinfomación acualizada
        listRecordsEntity=recordDao.getLast10RecordEntities();
        RecordListViewAdapter recordListViewAdapter=new RecordListViewAdapter(getContext(), listRecordsEntity);
        binding.listViewRecords.setAdapter(recordListViewAdapter);



        return binding.getRoot();

    }




}