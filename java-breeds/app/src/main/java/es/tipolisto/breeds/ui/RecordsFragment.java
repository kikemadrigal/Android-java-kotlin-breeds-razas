package es.tipolisto.breeds.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.ArrayDataSource;
import es.tipolisto.breeds.data.PreferencesManagaer;
import es.tipolisto.breeds.databinding.FragmentGameBinding;
import es.tipolisto.breeds.databinding.FragmentRecordsBinding;
import es.tipolisto.breeds.utils.Util;


public class RecordsFragment extends Fragment {
    private FragmentRecordsBinding binding=null;
    private PreferencesManagaer preferencesManagaer;

    public RecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View view=inflater.inflate(R.layout.fragment_records, container, false);
        binding= FragmentRecordsBinding.inflate(inflater, container, false);

        preferencesManagaer=new PreferencesManagaer(getContext());



        HashMap<Integer,String> recordList= ArrayDataSource.getMaprecordList();
        String text="";
        Iterator<Integer> iterator=recordList.keySet().iterator();
        while(iterator.hasNext()){
            Integer llave=(Integer) iterator.next();
            text =recordList.get(llave);
            binding.textViewHighScore.append("\n"+String.valueOf(llave)+" "+text);
        }



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}