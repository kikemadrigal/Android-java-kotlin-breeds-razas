package es.tipolisto.breeds.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.adapters.CatRecyclerViewAdapter;
import es.tipolisto.breeds.adapters.DogRecyclerViewAdapter;

import es.tipolisto.breeds.databinding.FragmentAnimalListBinding;
import es.tipolisto.breeds.model.Cat;
import es.tipolisto.breeds.model.DogListResponse;
import es.tipolisto.breeds.services.ICatsApiService;
import es.tipolisto.breeds.services.IDogApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalListFragment extends Fragment {
    private FragmentAnimalListBinding binding=null;
    private String modo;
    private RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "modo";



    public AnimalListFragment() {
    }
    /*public static AnimalListFragment newInstance(String modo) {
        AnimalListFragment fragment = new AnimalListFragment();
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
            Log.d("Mensaje","Mensaje desde fragment obtenido el modo "+modo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentAnimalListBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //View view=inflater.inflate(R.layout.fragment_animal_list,container,false);
        //return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (modo.equals("cat")){
            obtenerListaDeGatos();
        }else if (modo.equals("dog")){
            obtenerListaDePerros();
        }

    }

    private void obtenerListaDeGatos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Retrofit retrofit= RetrofitClient.getRetrofit("https://api.thecatapi.com/v1/");
        ICatsApiService iCatsApiService=retrofit.create(ICatsApiService.class);
        Call<List<Cat>> callListCats=iCatsApiService.getAllCats();
        callListCats.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                    List<Cat> listCats=response.body();
                    CatRecyclerViewAdapter catRecyclerViewAdapter=new CatRecyclerViewAdapter(listCats);
                            //binding.recyclerView.setAdapter(catRecyclerViewAdapter);
                    binding.recyclerView.setAdapter(catRecyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {

            }
        });
    }

    private void obtenerListaDePerros(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Retrofit retrofit=RetrofitClient.getRetrofit("https://dog.ceo/api/");
        //Para obtener la lista de Perros primero tenemos que obtener la raza de una imagen aleatoria
        IDogApiService iDogApiService=retrofit.create(IDogApiService.class);
        Call<DogListResponse> callDogResponse=iDogApiService.getListDog();
        callDogResponse.enqueue(new Callback<DogListResponse>() {
            @Override
            public void onResponse(Call<DogListResponse> call, Response<DogListResponse> response) {
                if (response.isSuccessful()){
                    DogListResponse dogListResponse =response.body();
                    List<String> dogImages= dogListResponse.getMessage();
                    DogRecyclerViewAdapter dogRecyclerViewAdapter=new DogRecyclerViewAdapter(dogImages);
                    binding.recyclerView.setAdapter(dogRecyclerViewAdapter);
                }else{
                    Log.d("Mensaje", "Sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<DogListResponse> call, Throwable t) {
                Log.d("Mensaje", "Fallo al obtener la respuesta");
            }
        });
    }
}