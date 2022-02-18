package es.tipolisto.breeds.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import es.tipolisto.breeds.R;
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
        Log.d("Mensaje","Al comienzo El modo es "+String.valueOf(preferencesManagaer.getDarkOnOff()));
        if(preferencesManagaer.getDarkOnOff()) binding.switchSettingsDarkTheme.setChecked(true);
        else binding.switchSettingsDarkTheme.setChecked(false);
        /*if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.ThemeDark);
        }else{
            setTheme(R.style.ThemeBreeds);
        }*/

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


        /*binding.switchSettingsDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("Mensaje","El modo es "+String.valueOf(preferencesManagaer.getDarkOnOff()));
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    preferencesManagaer.saveDarkOnOff(false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    preferencesManagaer.saveDarkOnOff(true);
                }
            }
        });*/
        binding.switchSettingsDarkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mensaje","El modo es "+String.valueOf(preferencesManagaer.getDarkOnOff()));

                if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
                    getActivity().setTheme(R.style.ThemeBreeds);
                    binding.switchSettingsDarkTheme.setChecked(false);
                    preferencesManagaer.saveDarkOnOff(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else{
                    getActivity().setTheme(R.style.ThemeDark);
                    binding.switchSettingsDarkTheme.setChecked(true);
                    preferencesManagaer.saveDarkOnOff(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });
    }


}