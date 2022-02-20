package es.tipolisto.breeds.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;




import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.ui.adapters.CatRecyclerViewAdapter;
import es.tipolisto.breeds.databinding.ActivityMainBinding;
import es.tipolisto.breeds.ui.dialogs.Dialog;
import es.tipolisto.breeds.utils.MediaPlayerClient;
import es.tipolisto.breeds.utils.Util;

public class MainActivity extends AppCompatActivity {


    private CatRecyclerViewAdapter catRecyclerViewAdapter;
    private MediaPlayerClient mediaPlayerClient;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.Declaramos una variable con el mismo nombre que el layout pero terminada en binding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PreferencesManagaer preferencesManagaer=new PreferencesManagaer(this);
        if(preferencesManagaer.getDarkOnOff()){
            setTheme(R.style.ThemeDark);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            setTheme(R.style.ThemeBreeds);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if (!Util.isNetworkConnected(getApplicationContext())) Dialog.showDialogNecessaryInternet(this);


        binding.imageButtonCat.setOnClickListener(view -> {
            mediaPlayerClient.playSound("button");
            Intent intent=new Intent(MainActivity.this, ContentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("modo", "cat");
            intent.putExtra("screen", "game");
            startActivity(intent);
            finish();
        });
        binding.imageButtonDog.setOnClickListener(view -> {
            mediaPlayerClient.playSound("button" );
            Intent intent=new Intent(MainActivity.this, ContentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("modo", "dog");
            intent.putExtra("screen", "game");
            startActivity(intent);
            finish();
        });
        binding.imageButtonRecords.setOnClickListener(view -> {
            mediaPlayerClient.playSound("button" );
            mediaPlayerClient.stopSound();
            Intent intent=new Intent(MainActivity.this, ContentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("modo", "nothing");
            intent.putExtra("screen", "records");
            startActivity(intent);
            finish();
        });
        binding.imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerClient.playSound("button" );
                mediaPlayerClient.stopSound();
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "nothing");
                intent.putExtra("screen", "settings");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        long currentTime=System.currentTimeMillis();
        if (back_pressed + 2000 < currentTime){
            Toast.makeText(this, getApplicationContext().getString(R.string.exit_app), Toast.LENGTH_LONG).show();
        }else {
            super.onBackPressed();
            finish();
        }
        back_pressed= System.currentTimeMillis();
    }
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mostrarDialogoSalir();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayerClient=new MediaPlayerClient(getBaseContext());
        mediaPlayerClient.playIntro();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerClient.releaseSound();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Util.MY_PERMISSIONS_REQUEST_WIFI_STATUS:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }
}