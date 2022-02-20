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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.databinding.FragmentGameBinding;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.ui.viewmodels.GameFragmentViewModel;
import es.tipolisto.breeds.utils.Constants;

/**
 * Esta clase primero solicita 3 gatos (solo id y nombre) aletarias
 * después asigna los 3 valores a los radio buttons de una forma aletaoria
 * después con el primer gato (solo id) hace una petición rest
 */

public class GameFragment extends Fragment {
    private FragmentGameBinding binding=null;
    //El modo nos sirve como bandera para hacer las peticiones REST de los gatos o los perros
    private GameFragmentViewModel viewModel;
    private String modo;
    private Boolean returnMenu;

    //interface
    private OnActionGame onActionGame;

    //Argumentos pasados en la llamada al fragment
    private static final String ARG_PARAM1 = "modo";
    private static final String ARG_PARAM2 = "returnMenu";
    public GameFragment() {
        returnMenu=false;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGameBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(GameFragmentViewModel.class);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.d("Mensaje","pasa por onViewCreated");
        onClickRadioButtons();

        if(getArguments()!=null){
            modo=getArguments().getString(ARG_PARAM1);
            returnMenu=getArguments().getBoolean(ARG_PARAM2);
        }

        //Controlamos el comportamiento del progressbar del viewModel
        viewModel.getMutableProgressBarVisible().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                binding.progressBar.setVisibility(View.VISIBLE);
            else
                binding.progressBar.setVisibility(View.GONE);
        });
        if (modo.equals("cat")){
            //Si hemos pinchado en el "mando" del toolbar es que hemos vuelto de ver la lista o de la descripción de raza
            //En ese caso retornamos la información almacenada en el viewModel
            if (returnMenu){
                String[] text=viewModel.getTextRadioButtons();
                binding.radioButton1.setText(text[0]);
                binding.radioButton2.setText(text[1]);
                binding.radioButton3.setText(text[2]);
            }else{
                setDataCat();
            }
            viewModel.getMutableCat().observe(getViewLifecycleOwner(), cat -> {
                //Cargamos la imagen en el imageView
                try{
                    binding.progressBar.setVisibility(View.VISIBLE);
                    Picasso.get().load(cat.getImage().getUrl())
                            .into(binding.imageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    binding.progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    Picasso.get().load(R.drawable.goback).into(binding.imageCat);
                                    binding.progressBar.setVisibility(View.GONE);
                                }
                            });
                    //Esto es para si hay un cambio de rotación conservar la url
                    viewModel.setUrlRestore(cat.getImage().getUrl());
                }catch(Exception ex){Log.d(Constants.LOG, ex.toString());}
            });
        }else if (modo.equals("dog")){
            if (returnMenu){
                String[] text=viewModel.getTextRadioButtons();
                binding.radioButton1.setText(text[0]);
                binding.radioButton2.setText(text[1]);
                binding.radioButton3.setText(text[2]);
                //Picasso.get().load(viewModel.getUrlRestore()).into(binding.imageView);
            }else {
                viewModel.updatePhotoDog();
                setDataDog();
            }
            //Este API tiene un fallo y a veces no te devuelve el objeto breeds, pruebalo:https://api.thedogapi.com/v1/images/search
            viewModel.getMutableDog().observe(getViewLifecycleOwner(), new Observer<Dog>() {
                @Override
                public void onChanged(Dog dogResponse) {
                    //setDataDog();
                    try{
                        Picasso.get().load(viewModel.getUrlRestore()).into(binding.imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                binding.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get().load(R.drawable.goback).into(binding.imageCat);
                                binding.progressBar.setVisibility(View.GONE);
                            }
                        });
                    }catch (Exception ex){Log.d(Constants.LOG, ex.toString());}
                }
            });
        }
    }
    /*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //Log.d("Mensaje","pasa por onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("returnRotation", true);
    }*/

    /*@Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            //Log.d("Mensaje","pasa por onViewStateRestored");
            returnMenu=savedInstanceState.getBoolean("returnMenu",false);
        }
    }*/



    private void onClickRadioButtons(){
        //binding.radioButton1.setOnClickListener(view -> onRadioButtonClicked(view));
        binding.radioButton1.setOnClickListener(this::onRadioButtonClicked);
        binding.radioButton2.setOnClickListener(this::onRadioButtonClicked);
        binding.radioButton3.setOnClickListener(this::onRadioButtonClicked);
    }

    private void setDataCat(){
        //Dame 3 razas aleatorias
        //Mientras sea verdad vuelve a pedir la lista de gatos
        Cat[] cats;
        do{
            cats=viewModel.get3RamdomCats();
        }while(viewModel.checkCatsEquals(cats));
        String [] textRadioButtons={cats[0].getName(), cats[1].getName(), cats[2].getName()};
        asignarTextoRadioButtons(textRadioButtons, true);
        //Metemos en el voewModel el array por si queremo recuperarlo
        viewModel.setTextRadioButtons(textRadioButtons);
        //Necesitamos guardar la foto y la raza (para ver si acierta en los radioButtons)
        viewModel.setBreedNameCat(cats[0].getName());
        //Le pedimos que nos devuelva una foto aletaoria del 1 gato
        viewModel.updatePhotoCat(cats[0].getId());
        Log.d("Mensaje", "Raza: "+cats[0].getName());
    }
    private void setDataDog(){
        Dog[] dogs;
        do{
            dogs=viewModel.get3RamdomDogs();
        }while(viewModel.checkDogsEquals(dogs));
        //El Dogs[0] se lo asignamos en el viewModel
        String breedDog1;
        if (dogs[1].getBreeds().size()==0)breedDog1="null";
        else breedDog1=dogs[1].getBreeds().get(0).getName();
        String breedDog2;
        if (dogs[2].getBreeds().size()==0)breedDog2="null2";
        else breedDog2=dogs[2].getBreeds().get(0).getName();
        //Le ponemos en la primera posición del array el nombre de la eaza obtenida en el viewmodel
        String [] textRadioButtons={viewModel.getBreednameDog(), breedDog1, breedDog2};
        breedDog1=viewModel.getBreednameDog();
        viewModel.setTextRadioButtons(textRadioButtons);
        asignarTextoRadioButtons(textRadioButtons, true);
        Log.d("Mensaje", "Raza "+breedDog1);
    }
    private void asignarTextoRadioButtons(String[] text, boolean mix) {
        if (mix){
            int numeroAleatorioDel1Al4=(int) (Math.random() * 4) + 1;
            switch(numeroAleatorioDel1Al4) {
                case 1:
                    binding.radioButton1.setText(text[0]);
                    binding.radioButton2.setText(text[1]);
                    binding.radioButton3.setText(text[2]);
                    break;
                case 2:
                    binding.radioButton1.setText(text[1]);
                    binding.radioButton2.setText(text[0]);
                    binding.radioButton3.setText(text[2]);
                    break;
                case 3:
                    binding.radioButton1.setText(text[2]);
                    binding.radioButton2.setText(text[1]);
                    binding.radioButton3.setText(text[0]);
                    break;
                case 4:
                    binding.radioButton1.setText(text[2]);
                    binding.radioButton2.setText(text[0]);
                    binding.radioButton3.setText(text[1]);
                    break;
            }
        }else{
            binding.radioButton1.setText(text[1]);
            binding.radioButton2.setText(text[3]);
            binding.radioButton3.setText(text[5]);
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
            setDataDog();
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
        void addScore();
        void subtractLive();
    }
}