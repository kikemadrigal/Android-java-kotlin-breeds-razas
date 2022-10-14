package es.tipolisto.breeds.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.tipolisto.breeds.R;


import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.ui.adapters.CatRecyclerViewAdapter;
import es.tipolisto.breeds.ui.adapters.DogRecyclerViewAdapter;
import es.tipolisto.breeds.ui.adapters.RecyclerViewClickListener;
import es.tipolisto.breeds.databinding.FragmentAnimalListBinding;
import es.tipolisto.breeds.data.model.Dog;
import es.tipolisto.breeds.ui.viewmodels.AnimalListFragmentViewModel;

public class AnimalListFragment extends Fragment {
    private FragmentAnimalListBinding binding=null;
    private AnimalListFragmentViewModel viewModel;

    private String modo;
    private OnClickItemRecycler onClickItemRecycler;
    private static final String ARG_PARAM1 = "modo";




    public AnimalListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            modo=getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof GameFragment.OnActionGame){
            onClickItemRecycler = (AnimalListFragment.OnClickItemRecycler) context;
        }else{
            throw  new RuntimeException(context.toString());
        }
    }
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentAnimalListBinding.inflate( inflater, container, false);
        binding.recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), binding.recyclerView, new RecyclerViewClickListener.OnItemViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Esto es solo para recuperarlo cuando se haga un cambio de rotación o se vaya a otro fragment
                viewModel.setPositinRecyclerView(position);
                TextView textView=view.findViewById(R.id.textViewRecyclerView);
                if (textView!=null){
                    onClickItemRecycler.onClickRecyclerAnimalList(textView.getText().toString());
                }
                //Log.d("Mensaje","click en "+String.valueOf(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AnimalListFragmentViewModel.class);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);


        if (modo.equals("cat")){
            viewModel.getListCat();
            viewModel.getMutableCatListResponse().observe(getViewLifecycleOwner(), listCatResponse -> {
                CatRecyclerViewAdapter catRecyclerViewAdapter=new CatRecyclerViewAdapter(listCatResponse);
                binding.recyclerView.setAdapter(catRecyclerViewAdapter);
                linearLayoutManager.scrollToPosition(viewModel.getPositinRecyclerView());
            });
            //Para mostrar u cultar el progressbar
            viewModel.getMutableProgressBarVisible().observe(getViewLifecycleOwner(), aBoolean -> {
                if (aBoolean)
                    binding.progressBarList.setVisibility(View.VISIBLE);
                else
                    binding.progressBarList.setVisibility(View.GONE);
            });
        }else if (modo.equals("dog")){
            viewModel.getListDog();
            viewModel.getMutableListBreedsDogResponse().observe(getViewLifecycleOwner(), new Observer<List<BreedsDog>>() {
                @Override
                public void onChanged(List<BreedsDog> listDogResponses) {
                    DogRecyclerViewAdapter dogRecyclerViewAdapter=new DogRecyclerViewAdapter(listDogResponses);
                    binding.recyclerView.setAdapter(dogRecyclerViewAdapter);
                    linearLayoutManager.scrollToPosition(viewModel.getPositinRecyclerView());
                }
            });
        }
    }
    public interface OnClickItemRecycler{
        void onClickRecyclerAnimalList(String breed);
    }
}