package es.tipolisto.breeds.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private PreferencesManagaer preferencesManagaer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferencesManagaer=new PreferencesManagaer(getContext());
        binding= FragmentSettingsBinding.inflate(inflater, container, false);
        preferencesManagaer=new PreferencesManagaer(getContext());

        if(preferencesManagaer.getMusicOnOff()) binding.switchSettingsMusicOnOff.setChecked(true);
        else binding.switchSettingsMusicOnOff.setChecked(false);
        binding.textViewWebSettings.setMovementMethod(LinkMovementMethod.getInstance());
        binding.textViewEmailSettings.setMovementMethod(LinkMovementMethod.getInstance());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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