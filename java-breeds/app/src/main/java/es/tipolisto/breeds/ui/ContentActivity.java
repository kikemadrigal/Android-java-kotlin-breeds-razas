package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.databinding.ActivityContentBinding;
import es.tipolisto.breeds.databinding.ToolbarBinding;

public class ContentActivity extends AppCompatActivity implements GameFragment.OnGameFragmentChange {
    private ActivityContentBinding binding;
    private int lives=7;
    private int score=0;
    private GameFragment gameFragment;

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
                Intent intent=new Intent(ContentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        gameFragment=new GameFragment();
        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            modo=bundle.getString("modo");
            //Log.d("Mensaje","Mensaje de contentactivity, obtenido el modo: "+modo);
        }
        cambiarFragment(gameFragment);

    }
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
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        this.lives=lives;
        this.score=score;
        binding.textViewLives.setText(String.valueOf(lives));
        binding.textViewScore.setText(String.valueOf(score));
    }
}