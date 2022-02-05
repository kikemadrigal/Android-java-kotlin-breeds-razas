package es.tipolisto.breeds.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import es.tipolisto.breeds.adapters.CatRecyclerViewAdapter;
import es.tipolisto.breeds.databinding.ActivityMainBinding;
import es.tipolisto.breeds.utils.MediaPlayerClient;

public class MainActivity extends AppCompatActivity {
    //1.Declaramos una variable con el mismo nombre que el layout pero terminada en binding
    private ActivityMainBinding binding;
    private CatRecyclerViewAdapter catRecyclerViewAdapter;
    //private MediaPlayer mediaPlayer;
    MediaPlayerClient mediaPlayerClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imageButtonCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerClient.playSound("button");
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "cat");
                startActivity(intent);
                finish();
            }
        });
        binding.imageButtonDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayerClient.playSound(getBaseContext(),mediaPlayer,"button");
                mediaPlayerClient.playSound("button" );
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "dog");
                startActivity(intent);
                finish();
            }
        });
        binding.imageButtonRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayerClient.playSound(getBaseContext(),mediaPlayer,"button");
                mediaPlayerClient.playSound("button" );
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "records");
                startActivity(intent);
                finish();
            }
        });
        binding.imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayerClient.playSound(getBaseContext(),mediaPlayer,"button");
                mediaPlayerClient.playSound("button" );
                Intent intent=new Intent(MainActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("modo", "settings");
                startActivity(intent);
                finish();
            }
        });
    }

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


}