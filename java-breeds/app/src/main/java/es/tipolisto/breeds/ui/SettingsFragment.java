package es.tipolisto.breeds.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.PreferencesManagaer;
import es.tipolisto.breeds.databinding.FragmentRecordsBinding;
import es.tipolisto.breeds.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private PreferencesManagaer preferencesManagaer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferencesManagaer=new PreferencesManagaer(getContext());
        //View view=inflater.inflate(R.layout.fragment_settings, container, false);
        binding= FragmentSettingsBinding.inflate(inflater, container, false);
        preferencesManagaer=new PreferencesManagaer(getContext());
        int highScore=preferencesManagaer.getHighRecord();
        binding.textViewSettingsRecords.setText(String.valueOf(highScore));
        if(preferencesManagaer.getMusicOnOff()) binding.switchSettingsMusicOnOff.setChecked(true);
        else binding.switchSettingsMusicOnOff.setChecked(false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSettingsDeleteRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesManagaer.deletePreferences();
                binding.textViewSettingsRecords.setText(String.valueOf(preferencesManagaer.getHighRecord()));
            }
        });

        binding.switchSettingsMusicOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preferencesManagaer.getMusicOnOff()){
                    preferencesManagaer.saveMusicOnOff(false);
                    binding.switchSettingsMusicOnOff.setChecked(false);
                }
                else{
                    preferencesManagaer.saveMusicOnOff(true);
                    binding.switchSettingsMusicOnOff.setChecked(true);
                }
            }
        });
    }
}