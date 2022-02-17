package es.tipolisto.breeds.ui;

import static es.tipolisto.breeds.ui.dialogs.Dialog.showDialogGameOver;
import static es.tipolisto.breeds.ui.dialogs.Dialog.showDialogNewRecord;
import static es.tipolisto.breeds.ui.dialogs.Dialog.showSialogExit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.databinding.ActivityContentBinding;
import es.tipolisto.breeds.databinding.ToolbarBinding;
import es.tipolisto.breeds.ui.dialogs.Dialog;
import es.tipolisto.breeds.ui.fragments.AnimalListFragment;
import es.tipolisto.breeds.ui.fragments.BreedFragment;
import es.tipolisto.breeds.ui.fragments.GameFragment;
import es.tipolisto.breeds.ui.fragments.RecordsFragment;
import es.tipolisto.breeds.ui.fragments.SettingsFragment;
import es.tipolisto.breeds.ui.viewmodels.ContentActivityViewModel;
import es.tipolisto.breeds.utils.MediaPlayerClient;
import es.tipolisto.breeds.utils.Util;

public class ContentActivity extends AppCompatActivity implements GameFragment.OnActionGame , AnimalListFragment.OnClickItemRecycler{
    private ActivityContentBinding binding;
    private ContentActivityViewModel viewModel;
    private int lives=7;
    private int score=0;
    private String modo;
    private String screeen;
    private boolean returnMenu;
    private GameFragment gameFragment;
    private AnimalListFragment animalListFragment;
    private BreedFragment breedFragment;
    private SettingsFragment settingsFragment;
    private RecordsFragment recordsFragment;
    //Para manejar la música:
    private MediaPlayerClient mediaPlayerClient;
    private MediaPlayerClient mediaPlayerClientEffects;
    //Para manejar las preferenvias
    private PreferencesManagaer preferencesManagaer;
    public ContentActivity(){
        lives=7;
        score=0;
        screeen="game";
        returnMenu=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (!Util.isNetworkConnected(getApplicationContext())) Dialog.showDialogNecessaryInternet(this);
        viewModel = new ViewModelProvider(this).get(ContentActivityViewModel.class);
        ToolbarBinding toolbarBinding = binding.toolbar;
        Toolbar toolbar=toolbarBinding.getRoot();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.flecha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerClientEffects.playSound("button");
                mostrarDialogoSalir();
            }
        });

        gameFragment=new GameFragment();
        animalListFragment=new AnimalListFragment();
        breedFragment=new BreedFragment();
        settingsFragment=new SettingsFragment();
        recordsFragment=new RecordsFragment();

        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            modo=bundle.getString("modo");
            screeen=bundle.getString("screen");
            //Log.d("Mensaje","Mensaje de contentactivity, obtenido el modo: "+modo);
            if (screeen.equals("game")) {
                screeen="game";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(gameFragment);
            }
            else if (screeen.equals("settings")) {
                screeen="settings";
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(settingsFragment);
            }
            else if (screeen.equals("records")) {
                screeen="records";
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(recordsFragment);
            }
            else if(screeen.equals("animalList")){
                screeen="animalList";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(animalListFragment);
            } else if(screeen.equals("breed")){
                screeen="breed";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(breedFragment);
            }
        }else{
            binding.linearLayout.setVisibility(View.VISIBLE);
            cambiarFragment(gameFragment);
        }

        binding.textViewLives.setText(String.valueOf(lives));
        binding.textViewScore.setText(String.valueOf(score));
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayerClient=new MediaPlayerClient(getBaseContext());
        mediaPlayerClientEffects=new MediaPlayerClient(getBaseContext());
        preferencesManagaer=new PreferencesManagaer(this);

        if(modo.equals("cat") || modo.equals("dog"))
            mediaPlayerClient.playInGame();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModel.setLives(lives);
        viewModel.setScore(score);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lives= viewModel.getLives();
        score= viewModel.getScore();
        binding.textViewLives.setText(String.valueOf(lives));
        binding.textViewScore.setText(String.valueOf(score));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerClient.releaseSound();
        mediaPlayerClientEffects.releaseSound();
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
        mediaPlayerClientEffects.playSound("button");
        switch (item.getItemId()) {
            case R.id.cat:
                modo="cat";
                screeen="animalList";
                cambiarFragment(animalListFragment);
                break;
            case R.id.dog:
                modo="dog";
                screeen="animalList";
                cambiarFragment(animalListFragment);
                break;
            case R.id.game:
                screeen="game";
                Log.d("Mensaje", "Clik en returmenu");
                returnMenu=true;
                cambiarFragment(gameFragment);
                break;
        }
        return true;
    }

    @Override
    public void addScore() {
        Toast.makeText(this, R.string.success, Toast.LENGTH_LONG).show();
        mediaPlayerClientEffects.playSound("success");
        score+=1;
        binding.textViewScore.setText(String.valueOf(score));
    }

    @Override
    public void subtractLive() {
        Toast.makeText(this, R.string.failure, Toast.LENGTH_LONG).show();
        lives-=1;
        mediaPlayerClientEffects.playSound("failure");
        viewModel.setLives(lives);
        binding.textViewLives.setText(String.valueOf(lives));
        //Si se ha quedo sin vidas mostramos un alertDialog
        if (this.lives<=0){
            //Si no hay record
            if (!checkRecord()){
                showDialogGameOver(this);
                //Todo: crear contador de milisegundos

            }else{
                mostrarDiaNuevorecord();
            }
        }
    }

    private void cambiarFragment(Fragment fragment) {
        Bundle bundle=new Bundle();
        bundle.putString("modo",modo);
        bundle.putString("screen",screeen);
        bundle.putBoolean("returnMenu",returnMenu);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFragments,fragment);
        //Con esto creamos un historial de fragments o pila de procesos con la información almacenada
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void mostrarDialogoSalir() {
        if (score>0) {
            showSialogExit(this);
            cambiarFragment(gameFragment);
        }else{
            exitTomenu();
        }
        super.onBackPressed();
    }
    public void mostrarDiaNuevorecord() {
        if (score>0) {
            showDialogNewRecord(this,score);
            cambiarFragment(gameFragment);
        }else{
            exitTomenu();
        }
        super.onBackPressed();
    }
    private void exitTomenu(){
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
            /*Intent intent=new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
        }
        return newRecord;
    }

    @Override
    public void onClickRecyclerAnimalList(String breed) {
        mediaPlayerClientEffects.playSound("button");
        Bundle bundle=new Bundle();
        bundle.putString("modo",modo);
        bundle.putString("breed",breed);
        BreedFragment breedFragment=new BreedFragment();
        breedFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFragments,breedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }






}