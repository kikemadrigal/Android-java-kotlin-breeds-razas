package es.tipolisto.breeds.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.squareup.picasso.Picasso;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.databinding.FragmentGameBinding;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.network.RetrofitClient;
import es.tipolisto.breeds.data.model.DogResponse;
import es.tipolisto.breeds.ui.viewmodels.GameFragmentViewModel;

/**
 * Esta clase mostrará una foto de un gato o un perro
 * en la parte inferior aparecerán unos radioButtons con las razas
 * tendrás que seleccionar la correcta para conseguir el record de puntos
 */

public class GameFragment extends Fragment {
    private FragmentGameBinding binding=null;
    //El modo nos sirve como bandera para hacer las peticiones REST de los gatos o los perros
    private GameFragmentViewModel viewModel;
    private String modo;
    private Boolean returnMenu;
    //private String breed_name;
    private int lives=7;
    private int score=0;



    //interface
    private OnActionGame onActionGame;


    private static final String ARG_PARAM1 = "modo";
    private static final String ARG_PARAM2 = "returnMenu";
    public GameFragment() {
        returnMenu=false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGameBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(GameFragmentViewModel.class);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Mensaje","pasa por onViewCreated");
        onClickRadioButtons();
        if(getArguments()!=null){
            modo=getArguments().getString(ARG_PARAM1);
            returnMenu=getArguments().getBoolean(ARG_PARAM2);
        }
        //Controlamos el comportamiento del progressbar del viewModel
        viewModel.getMutableProgressBarVisible().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    binding.progressBar.setVisibility(View.VISIBLE);
                else
                    binding.progressBar.setVisibility(View.GONE);
            }
        });
        if (modo.equals("cat")){
            //Si hemos pinchado en el "mando" del toolbar es que hemos vuelto de ver la lista o de la descripción de raza
            //En ese caso retornamos la información almacenada en el viewModel
            if (returnMenu){
                String[] idAndBreed=viewModel.getTextRadioButtons();
                asignarTextoRadioButtons(idAndBreed, true);
                Picasso.get().load(viewModel.getUrlRestore()).into(binding.imageView);
                Log.d("Mensaje", "Recuperado!!! id "+idAndBreed[0]+" name "+idAndBreed[1]);
            }else{
                setDataCat();
            }
            //Controlamos cuando la lista cambie
            viewModel.getMutableCat().observe(getViewLifecycleOwner(), new Observer<Cat>() {
                @Override
                public void onChanged(Cat cat) {
                    try {
                        Picasso.get().load(cat.getUrl()).into(binding.imageView);
                        //Esto es para si hay un cambio de rotación conservar la url
                        viewModel.setUrlRestore(cat.getUrl());
                    } catch (Exception e) {

                    }
                }
            });


        }else if (modo.equals("dog")){
            if (returnMenu){
                String[] idAndBreed=viewModel.getTextRadioButtons();
                asignarTextoRadioButtons(idAndBreed, true);
                Picasso.get().load(viewModel.getUrlRestore()).into(binding.imageView);
                Log.d("Mensaje", "Recuperado!!! id "+idAndBreed[0]+" name "+idAndBreed[1]);
            }else {
                viewModel.updatePhotoDog();
            }
            //Este API tiene un fallo y a veces no te devuelve el objeto breeds, pruebalo:https://api.thedogapi.com/v1/images/search
            viewModel.getMutableDog().observe(getViewLifecycleOwner(), new Observer<DogResponse>() {
                @Override
                public void onChanged(DogResponse dogResponse) {
                    String[] dogs=viewModel.dameUnIdYNombreRazaDe3Aleatorios("dog");
                    //Le ponemos en la primera posición del array el nombre de la eaza obtenida en el viewmodel
                    dogs[1]=viewModel.getBreednameDog();
                    if(dogs[1]==null) dogs[1]="null";
                    viewModel.setTextRadioButtons(dogs);
                    asignarTextoRadioButtons(dogs, true);
                    try{
                        Picasso.get().load(viewModel.getUrlRestore()).into(binding.imageView);
                    }catch (Exception ex){
                        Picasso.get().load(R.drawable.goback).into(binding.imageView);
                    }
                }
            });


        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //Log.d("Mensaje","pasa por onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("returMenu", true);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            Log.d("Mensaje","pasa por onViewStateRestored");
            returnMenu=savedInstanceState.getBoolean("returnMenu",false);
        }
    }



    private void onClickRadioButtons(){
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
    private void setDataCat(){
        //Dame 3 razas aleatorias
        String[] idAndBreed=viewModel.dameUnIdYNombreRazaDe3Aleatorios("cat");
        //viewModel.setBreedNameCat(idAndBreed[1]);
        //Metemos en el voewModel el array por si queremo recuprarlo
        viewModel.setTextRadioButtons(idAndBreed);
        //Le pedimos que nos devuelva una foto aletaoria de la 1 raza
        viewModel.updatePhotoCat(idAndBreed[0]);
        asignarTextoRadioButtons(idAndBreed, true);
        //Necesitamos guardar la foto y la raza (para ver si acierta en los radioButtons)
        viewModel.setBreedNameCat(idAndBreed[1]);
        viewModel.updatePhotoCat(idAndBreed[0]);
        Log.d("Mensaje", "id "+idAndBreed[0]+" name "+idAndBreed[1]);
    }
    private void asignarTextoRadioButtons(String[] idAndBreed, boolean mix) {
        if (mix){
            int numeroAleatorioDel1Al3=(int) (Math.random() * 3) + 1;
            switch(numeroAleatorioDel1Al3) {
                case 1:
                    binding.radioButton1.setText(idAndBreed[1]);
                    binding.radioButton2.setText(idAndBreed[3]);
                    binding.radioButton3.setText(idAndBreed[5]);
                    break;
                case 2:
                    binding.radioButton1.setText(idAndBreed[3]);
                    binding.radioButton2.setText(idAndBreed[1]);
                    binding.radioButton3.setText(idAndBreed[5]);
                    break;
                case 3:
                    binding.radioButton1.setText(idAndBreed[5]);
                    binding.radioButton2.setText(idAndBreed[3]);
                    binding.radioButton3.setText(idAndBreed[1]);
                    break;
            }
        }else{
            binding.radioButton1.setText(idAndBreed[1]);
            binding.radioButton2.setText(idAndBreed[3]);
            binding.radioButton3.setText(idAndBreed[5]);
        }

    }




    //Cuando haya un click aumentaremos los puntos o disminimos las vidas
    public void onRadioButtonClicked(View view) {
        String guardado="";
        String textoSeleccionado="";
        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button1:
                textoSeleccionado=binding.radioButton1.getText().toString();
                break;
            case R.id.radio_button2:
                textoSeleccionado=binding.radioButton2.getText().toString();
                break;
            case R.id.radio_button3:
                textoSeleccionado=binding.radioButton3.getText().toString();
                break;
        }


        //Ponemos los botones a falso
        binding.radioButton1.setChecked(false);
        binding.radioButton2.setChecked(false);
        binding.radioButton3.setChecked(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (modo.equals("cat")){
            guardado=viewModel.getBreedNameCat();
            setDataCat();
        }else if(modo.equals("dog")){
            guardado=viewModel.getBreednameDog();
            viewModel.updatePhotoDog();
            String[] dogs=viewModel.dameUnIdYNombreRazaDe3Aleatorios("dog");
            //Le ponemos en la primera posición del array el nombre de la eaza obtenida en el viewmodel
            dogs[1]=viewModel.getBreednameDog();
            viewModel.setTextRadioButtons(dogs);
            asignarTextoRadioButtons(dogs, true);
        }
        if (textoSeleccionado.equals(guardado)){
            onActionGame.addScore();
        }else{
            onActionGame.subtractLive();
        }
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
        if(context instanceof OnActionGame){
            onActionGame   = (OnActionGame) context;
        }else{
            throw  new RuntimeException(context.toString());
        }
    }

    public interface OnActionGame{
        public void addScore();
        public void subtractLive();
    }
}