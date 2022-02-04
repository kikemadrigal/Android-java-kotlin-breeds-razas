package es.tipolisto.breeds.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.databinding.FragmentGameBinding;
import es.tipolisto.breeds.model.Cat;
import es.tipolisto.breeds.model.DogResponse;
import es.tipolisto.breeds.services.ArrayDataSource;
import es.tipolisto.breeds.services.ICatsApiService;
import es.tipolisto.breeds.services.IDogApiService;
import es.tipolisto.breeds.services.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Esta clase mostrará una foto de un gato o un perro
 * en la parte inferior aparecerán unos radioButtons con las razas
 * tendrás que seleccionar la correcta para conseguir el record de puntos
 */

public class GameFragment extends Fragment {
    private FragmentGameBinding binding=null;
    private String modo;
    private String breed_name;
    private int[] buttons=new int[3];
    private int lives=7;
    private int score=0;

    private OnGameFragmentChange fragmentChange;

    private static final String ARG_PARAM1 = "modo";
    public GameFragment() {

    }
    /*public static GameFragment newInstance(String modo) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, modo);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            modo=getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (modo.equals("cat")){
            actualizarImagenYRadioButtonsGatos();
        }else if (modo.equals("dog")){
            actualizarImagenYRadioButtonsPerros();
        }
        binding.radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);
            }
        });
        binding.radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);
            }
        });
        binding.radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);
            }
        });
    }



    private String obtenerRazaDelGatoArray(){
        //Obtenemos los strings de los botones
        String boton1=binding.radioButton1.getText().toString();
        String boton2=binding.radioButton1.getText().toString();
        String boton3=binding.radioButton1.getText().toString();
        String newBreedName="";
        for (int i=0 ;i<3;i++){
            if (buttons[i]==0){
                int numeroAleatorio=(int) (Math.random() * ArrayDataSource.catBreeds.length-1) + 1;
                newBreedName=ArrayDataSource.catBreeds[numeroAleatorio];
                //Si es igual
                while (newBreedName.equals(boton1) || newBreedName.equals(boton2) || newBreedName.equals(boton3)){
                    numeroAleatorio=(int) (Math.random() * ArrayDataSource.catBreeds.length-1) + 1;
                    newBreedName=ArrayDataSource.catBreeds[numeroAleatorio];
                    buttons[i]=1;
                }
            }
        }
        return newBreedName;
    }
    private String obtenerRazaDelPerroArray(){
        //Obtenemos los strings de los botones
        String boton1=binding.radioButton1.getText().toString();
        String boton2=binding.radioButton1.getText().toString();
        String boton3=binding.radioButton1.getText().toString();
        String newBreedName="";
        for (int i=0 ;i<3;i++){
            if (buttons[i]==0){
                int numeroAleatorio=(int) (Math.random() * ArrayDataSource.dogsBreeds.length-1) + 1;
                newBreedName=ArrayDataSource.dogsBreeds[numeroAleatorio];
                //Si es igual
                while (newBreedName.equals(boton1) || newBreedName.equals(boton2) || newBreedName.equals(boton3)){
                    numeroAleatorio=(int) (Math.random() * ArrayDataSource.dogsBreeds.length-1) + 1;
                    newBreedName=ArrayDataSource.dogsBreeds[numeroAleatorio];
                    buttons[i]=1;
                }
            }
        }
        return newBreedName;
    }

    //La imagen La obtendremos desde internet, después actualizamos el texto de los radiobuttons con las razas
    private void actualizarImagenYRadioButtonsGatos(){
        binding.progressBar.setVisibility(View.VISIBLE);
        //Para obtenr un gato la API necesita que le metamos una raza en la URL
        //1.Obtenemos una raza aleatoria de la base de datos
        int numeroAleatorio=(int) (Math.random() * ArrayDataSource.catBreeds.length-1) + 1;
        breed_name=ArrayDataSource.catBreeds[numeroAleatorio];
        Log.d("Mensaje","Raza: "+breed_name);
        //Retorfit nos devuelve la foto
        Retrofit retrofit= RetrofitClient.getRetrofit("https://api.thecatapi.com/v1/");
        ICatsApiService iCatsApiService=retrofit.create(ICatsApiService.class);
        //Call<List<Cat>> callCat=iCatsApiService.getcatByBreed(breed_name);
        Call<List<Cat>> callCat=iCatsApiService.getcatByBreed("Donskoy");
        callCat.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                //La url en realidad devuelve un array con 1 solo objeto cat
                List<Cat> listcats=response.body();
                Picasso.get().load(listcats.get(0).getUrl()).into(binding.imageCat);
                //3:le ponemos la raza a los radiobutons
                asignarValoresRadioButtons();
                binding.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                //Log.d("Mensaje","Mal");
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }
    private void actualizarImagenYRadioButtonsPerros(){
        binding.progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Retorfit nos devuelve un objeto DogResponse el cual contiene un String con la foto y un String con la respuesta
        //Retrofit retrofit= RetrofitClient.getRetrofit("https://dog.ceo/api/");
        IDogApiService iDogApiService=retrofit.create(IDogApiService.class);
        Call<DogResponse> callDogResponse=iDogApiService.getDog();
        callDogResponse.enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if(response.isSuccessful()){
                    DogResponse dogResponse=response.body();
                    //Del string con la foto sacamos la raza
                    String url=dogResponse.getMessage();
                    Picasso.get().load(url).into(binding.imageCat);
                    int posicion4barra=url.indexOf("/",25);
                    int posicion5Barra=url.indexOf("/",32);
                    breed_name=url.substring(posicion4barra+1,posicion5Barra);
                    //Log.d("Mensaje","Raza: "+breed_name);

                    asignarValoresRadioButtons();
                    binding.progressBar.setVisibility(View.GONE);
                }else{
                    //Log.d("Mensaje","El response está vacío");
                }

            }
            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                //Log.d("Mensaje","Hubo un fallo");
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }
    private void asignarValoresRadioButtons() {
        //Le metemos la raza a uno de los tres botones
        int numeroAleatorioDel1Al3=(int) (Math.random() * 3) + 1;
        switch(numeroAleatorioDel1Al3){
            case 1:
                binding.radioButton1.setText(breed_name);
                buttons[0]=1;
                buttons[1]=0;
                buttons[2]=0;
                if (modo.equals("cat")){
                    binding.radioButton2.setText(obtenerRazaDelGatoArray());
                    binding.radioButton3.setText(obtenerRazaDelGatoArray());
                }else if (modo.equals("dog")){
                    binding.radioButton2.setText(obtenerRazaDelPerroArray());
                    binding.radioButton3.setText(obtenerRazaDelPerroArray());
                }

                break;
            case 2:
                binding.radioButton2.setText(breed_name);
                buttons[0]=0;
                buttons[1]=1;
                buttons[2]=0;
                if (modo.equals("cat")) {
                    binding.radioButton1.setText(obtenerRazaDelGatoArray());
                    binding.radioButton3.setText(obtenerRazaDelGatoArray());
                }else if (modo.equals("dog")){
                    binding.radioButton1.setText(obtenerRazaDelPerroArray());
                    binding.radioButton3.setText(obtenerRazaDelPerroArray());
                }
                break;
            case 3:
                binding.radioButton3.setText(breed_name);
                buttons[0]=0;
                buttons[1]=0;
                buttons[2]=1;
                if (modo.equals("cat")) {
                    binding.radioButton1.setText(obtenerRazaDelGatoArray());
                    binding.radioButton2.setText(obtenerRazaDelGatoArray());
                }else if (modo.equals("dog")){
                    binding.radioButton1.setText(obtenerRazaDelPerroArray());
                    binding.radioButton2.setText(obtenerRazaDelPerroArray());
                }
                break;
        }
        if(!breed_name.equals(breed_name)){
            //binding.radioButton1.setText();
        }

    }





    public void onRadioButtonClicked(View view) {
        String textoSeleccionado="";
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        int numeroAleatorio=(int) (Math.random() * ArrayDataSource.catBreeds.length-1) + 1;
        String new_breed_name=ArrayDataSource.catBreeds[numeroAleatorio];
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button1:
                if (checked){
                    //Toast.makeText(this, "Click en 1", Toast.LENGTH_LONG).show();
                    textoSeleccionado=binding.radioButton1.getText().toString();
                }
                break;
            case R.id.radio_button2:
                if (checked){
                    //Toast.makeText(this, "Click en 2", Toast.LENGTH_LONG).show();
                    textoSeleccionado=binding.radioButton2.getText().toString();
                }

                break;
            case R.id.radio_button3:
                if (checked){
                    //Toast.makeText(this, "Click en 3", Toast.LENGTH_LONG).show();
                    textoSeleccionado=binding.radioButton3.getText().toString();
                }
                break;
        }
        if (textoSeleccionado.equals(breed_name)){
            Toast.makeText(getContext(), "Exito!!", Toast.LENGTH_LONG).show();
            //score=Integer.valueOf(binding.textViewScoreCat.getText().toString());
            score+=1;

            //binding.textViewScoreCat.setText(String.valueOf(score));
        }else{
            Toast.makeText(getContext(), "Fallo!!", Toast.LENGTH_LONG).show();
            lives-=1;
            if (lives<=0){
                Intent intent=new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                //binding.textViewLivesCat.setText(String.valueOf(lives));
            }


        }
        fragmentChange.updateLivesAndPoints(lives,score);
        binding.radioButton1.setChecked(false);
        binding.radioButton2.setChecked(false);
        binding.radioButton3.setChecked(false);
        if(modo.equals("cat"))
            actualizarImagenYRadioButtonsGatos();
        else if(modo.equals("dog"))
            actualizarImagenYRadioButtonsPerros();

    }

    /**
     * Para Crear un escuchador de cambios dentro del fragment tenemos que crear una interface
     * 1.Creamos la interface OnGameFragmentChange con su método público
     * 2.Vamos a la clase que quiere este comportamiento y la implementamos con GameFragment.OnGameFragmentChange
     * 3.Creamos como atributos de la clase del fragment la interface: private OnGameFragmentChange fragmentChange;
     * 4 En el botón que hereda este comportamiento le ponemos: fragmentChange.updateLivesAndPoints(lives,score);
     * 5.Sobreescribimos el método onAttach del fragment y le ponemos:
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnGameFragmentChange){
            fragmentChange   = (OnGameFragmentChange) context;
        }else{
            throw  new RuntimeException(context.toString());
        }
    }

    public interface OnGameFragmentChange{
        public void updateLivesAndPoints(int lives, int score);
    }
}