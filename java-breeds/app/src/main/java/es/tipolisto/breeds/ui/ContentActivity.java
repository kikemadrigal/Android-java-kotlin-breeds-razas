package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.PreferencesManagaer;
import es.tipolisto.breeds.databinding.ActivityContentBinding;
import es.tipolisto.breeds.databinding.ToolbarBinding;
import es.tipolisto.breeds.utils.MediaPlayerClient;

public class ContentActivity extends AppCompatActivity implements GameFragment.OnGameFragmentChange {
    private ActivityContentBinding binding;
    private int lives=7;
    private int score=0;
    private GameFragment gameFragment;
    //Para manejar la música:
    private MediaPlayerClient mediaPlayerClient;
    //Para manejar las preferenvias
    private PreferencesManagaer preferencesManagaer;
    String modo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ToolbarBinding toolbarBinding = binding.toolbar;
        Toolbar toolbar=toolbarBinding.getRoot();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.flecha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerClient.playSound("button");
                mostrarDialogoSalir();
            }
        });

        gameFragment=new GameFragment();
        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            modo=bundle.getString("modo");
            //Log.d("Mensaje","Mensaje de contentactivity, obtenido el modo: "+modo);
            if (modo.equals("settings")) {
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(new SettingsFragment());
            }
            else if (modo.equals("records")) {
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(new RecordsFragment());
            }
            else {
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(gameFragment);
            }
        }else{
            binding.linearLayout.setVisibility(View.VISIBLE);
            cambiarFragment(gameFragment);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayerClient=new MediaPlayerClient(getBaseContext());
        preferencesManagaer=new PreferencesManagaer(this);
        if(modo.equals("cat") || modo.equals("dog"))
            mediaPlayerClient.playInGame();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerClient.releaseSound();
    }


    public void mostrarDialogoSalir() {
        if (score>0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensaje");
            builder.setMessage("¿Estas seguro que deseas salir?, perderás la puntuación: "+String.valueOf(score));
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
            builder.create().show();
        }else{
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mostrarDialogoSalir();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //Mostramos o no los botones según nos convenga
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (modo.equals("cat")){
            menu.findItem(R.id.cat).setVisible(true);
            menu.findItem(R.id.dog).setVisible(false);
            menu.findItem(R.id.game).setVisible(true);
        }else if(modo.equals("dog")){
            menu.findItem(R.id.cat).setVisible(false);
            menu.findItem(R.id.dog).setVisible(true);
            menu.findItem(R.id.game).setVisible(true);
        }else{
            menu.findItem(R.id.cat).setVisible(false);
            menu.findItem(R.id.dog).setVisible(false);
            menu.findItem(R.id.game).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediaPlayerClient.playSound("button");
        switch (item.getItemId()) {
            case R.id.cat:
                modo="cat";
                cambiarFragment(new AnimalListFragment());
                break;
            case R.id.dog:
                modo="dog";
                cambiarFragment(new AnimalListFragment());
                break;
            case R.id.game:
                cambiarFragment(gameFragment);
                break;
        }
        return true;
    }
    private void cambiarFragment(Fragment fragment) {
        Bundle bundle=new Bundle();
        bundle.putString("modo",modo);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFragments,fragment);
        //Con esto creamos un historial de fragments o pila de procesos con la información almacenada
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void updateLivesAndPoints(int lives, int score) {
        //Si se ha perdido una vida, el sonido será de fallo
        if (this.lives>lives){
            Toast.makeText(this, "Failure!!", Toast.LENGTH_LONG).show();
            mediaPlayerClient.playSound("failure");
        }
        //Si se ha sumado puntos es que hemos acertado
        if (this.score<score){
            Toast.makeText(this, "Success!!", Toast.LENGTH_LONG).show();
            mediaPlayerClient.playSound("success");
        }
        this.lives=lives;
        this.score=score;
        binding.textViewLives.setText(String.valueOf(lives));
        binding.textViewScore.setText(String.valueOf(score));
        //Si se ha quedo sin vidas mostramos un alertDialog
        if (this.lives<=0){
            if (!checkRecord()){
                //showGameOver();
                cambiarFragment(new GameOverFragment());
                //Pasados 5 segundos iremos al main
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }
    }
    private boolean checkRecord() {

        int highScore= preferencesManagaer.getHighRecord();
        //Log.d("Mensaje","checkRecord dice: "+String.valueOf(highScore));
        //int highScore=sharedPreferences.getInt("highscore",0);
        boolean newRecord=false;
        if (score>highScore){
            newRecord=true;
            //Guardamos el nuevo record
            preferencesManagaer.saveNewRecord(score);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Breeds says");
            builder.setMessage("New record: "+String.valueOf(score));
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            builder.create().show();
            //Si no hay nuevo highscore salimos
        }else{
            Intent intent=new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return newRecord;
    }
    /*private void showGameOver(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Breeds says");
        builder.setMessage("Game over"+String.valueOf(score));
        Dialog dialog=builder.create();
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }*/

}