package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.adapters.CatRecyclerViewAdapter;
import es.tipolisto.breeds.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //1.Declaramos una variable con el mismo nombre que el layout pero terminada en binding
    private ActivityMainBinding binding;
    private CatRecyclerViewAdapter catRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2.La onflamos
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //3.Le metemos al setcontent el binding
        View view = binding.getRoot();
        setContentView(view);

        //4.Accedemos a los componentes
        binding.imageButtonCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "cat");
                startActivity(intent);
            }
        });
        binding.imageButtonDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "dog");
                startActivity(intent);
            }
        });
    }

}