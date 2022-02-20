package es.tipolisto.breeds.ui;

import static es.tipolisto.breeds.ui.dialogs.Dialog.showDialogGameOver;
import static es.tipolisto.breeds.ui.dialogs.Dialog.showDialogNewRecord;
import static es.tipolisto.breeds.ui.dialogs.Dialog.showSialogExit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
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
    private int lives;
    private int score;
    private String modo;
    private String screen;
    private boolean returnMenu;
    private GameFragment gameFragment;
    private AnimalListFragment animalListFragment;

    //Para manejar la música:
    private MediaPlayerClient mediaPlayerClient;
    private MediaPlayerClient mediaPlayerClientEffects;
    //Para manejar las preferenvias
    private PreferencesManagaer preferencesManagaer;
    public ContentActivity(){
        lives=7;
        score=0;
        screen="game";
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
        toolbar.setNavigationOnClickListener(view -> {
            mediaPlayerClientEffects.playSound("button");
            mostrarDialogoSalir();
        });

        BreedFragment breedFragment=new BreedFragment();
        SettingsFragment settingsFragment=new SettingsFragment();
        RecordsFragment recordsFragment=new RecordsFragment();

        gameFragment=new GameFragment();
        animalListFragment=new AnimalListFragment();



        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            modo = bundle.getString("modo");
            screen = bundle.getString("screen");
            //Log.d("Mensaje","Mensaje de contentactivity, obtenido el modo: "+modo);
            switch (screen) {
                case "game":
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    cambiarFragment(gameFragment);
                    break;
                case "settings":
                    binding.linearLayout.setVisibility(View.GONE);
                    cambiarFragment(settingsFragment);
                    break;
                case "records":
                    binding.linearLayout.setVisibility(View.GONE);
                    cambiarFragment(recordsFragment);
                    break;
                case "animalList":
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    cambiarFragment(animalListFragment);
                    break;
                case "breed":
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    cambiarFragment(breedFragment);
                    break;
                default:
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    cambiarFragment(gameFragment);
            }
            /*if (screen.equals("game")) {
                //screen="game";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(gameFragment);
            }
            else if (screen.equals("settings")) {
                //screen="settings";
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(settingsFragment);
            }
            else if (screen.equals("records")) {
                //screen="records";
                binding.linearLayout.setVisibility(View.GONE);
                cambiarFragment(recordsFragment);
            }
            else if(screen.equals("animalList")){
                //screen="animalList";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(animalListFragment);
            } else if(screen.equals("breed")){
                //screen="breed";
                binding.linearLayout.setVisibility(View.VISIBLE);
                cambiarFragment(breedFragment);
            }
        }else{
            binding.linearLayout.setVisibility(View.VISIBLE);
            cambiarFragment(gameFragment);
        }*/
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
    public void onBackPressed() {
        //super.onBackPressed();
        mostrarDialogoSalir();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerClient.releaseSound();
        mediaPlayerClientEffects.releaseSound();
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
                //screeen="animalList";
                cambiarFragment(animalListFragment);
                break;
            case R.id.dog:
                modo="dog";
                screen="animalList";
                cambiarFragment(animalListFragment);
                break;
            case R.id.game:
                screen="game";
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
            //Si la puntuación no es 0
            if (score>0) {
                int highScore= preferencesManagaer.getHighRecord();
                //Comprobamos que hay un nuevo rcord
                if (score>highScore){
                    showDialogNewRecord(this,score);
                }else{
                    showDialogGameOver(this);
                }
            }else{
                cambiarFragment(gameFragment);
            }
        }
    }

    private void cambiarFragment(Fragment fragment) {
        Bundle bundle=new Bundle();
        bundle.putString("modo",modo);
        bundle.putString("screen",screen);
        bundle.putBoolean("returnMenu",returnMenu);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFragments,fragment);
        //Con esto creamos un historial de fragments o pila de procesos con la información almacenada
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void mostrarDialogoSalir() {
        if (score>0) {
            showSialogExit(this);
            cambiarFragment(gameFragment);
        }else{
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
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
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }






}