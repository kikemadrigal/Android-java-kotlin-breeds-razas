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
        String nameRecord=preferencesManagaer.getnameRecord();
        binding.textViewSettingsRecords.setText(nameRecord+": "+String.valueOf(score));
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
        List<RecordEntity> listRecordsEntity=recordDao.getAllRecordEntities();
        RecordListViewAdapter recordListViewAdapter=new RecordListViewAdapter(getContext(), listRecordsEntity);
        binding.listViewRecords.setAdapter(recordListViewAdapter);

        Log.d("Mensaje","Tamano lista"+listRecordsEntity.size());
        return binding.getRoot();

    }


}