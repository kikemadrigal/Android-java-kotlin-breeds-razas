package es.tipolisto.breeds.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.databinding.FragmentBreedBinding;

import es.tipolisto.breeds.data.model.Breeds;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.DogResponse;
import es.tipolisto.breeds.data.network.RetrofitClient;
import es.tipolisto.breeds.ui.viewmodels.BreedFragmentViewModel;


public class BreedFragment extends Fragment {

    private FragmentBreedBinding binding;
    private static final String ARG_PARAM1 = "breed";
    private static final String ARG_PARAM2= "modo";
    private String breedName;
    private String mode;
    private RetrofitClient retrofitClient;
    private BreedFragmentViewModel viewModel;

    public BreedFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            breedName = getArguments().getString(ARG_PARAM1);
            mode = getArguments().getString(ARG_PARAM2);
            retrofitClient=new RetrofitClient();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentBreedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(BreedFragmentViewModel.class);
        viewModel.getMutableLiveDataProgressBarVisible().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    binding.progressBar.setVisibility(View.VISIBLE);
                else
                    binding.progressBar.setVisibility(View.GONE);
            }
        });
        if (getArguments() != null) {
            if (mode.equals("cat")) {
                String idBreedCat = viewModel.generateIdCat(breedName);
                viewModel.getCat(idBreedCat);
                viewModel.getMutableLiveDataCat().observe(getViewLifecycleOwner(), new Observer<Cat>() {
                    @Override
                    public void onChanged(Cat cat) {
                        setValuesCatViews(cat);
                    }
                });
            } else if (mode.equals("dog")) {
                String breedId = viewModel.generateIdDog(breedName);
                viewModel.getDog(breedId);
                viewModel.getMutableLiveDataDogresponse().observe(getViewLifecycleOwner(), new Observer<DogResponse>() {
                    @Override
                    public void onChanged(DogResponse dogResponse) {
                        setValuesDogViews(dogResponse);
                    }
                });
            }
            binding.textViewBreedFragment.setText("modo: " + mode + " raza " + breedName);
        }
    }



    private void setValuesCatViews(Cat cat) {
        try {
            Picasso.get().load(cat.getUrl()).into(binding.imageViewBreedFragment);
        } catch (Exception e) {
            Picasso.get().load(R.drawable.goback).into(binding.imageViewBreedFragment);
            e.printStackTrace();
        }

        Breeds breed = cat.getBreeds().get(0);
        String content = breed.getName() + "\n";
        content += breed.getTemperament() + "\n";
        content += breed.getOrigin() + "\n";
        content += breed.getCountry_code() + "\n";
        content += breed.getDescription() + "\n";
        binding.textViewBreedFragment.setText(content);
        binding.textViewWikipediaBreedFragment.setMovementMethod(LinkMovementMethod.getInstance());
        String wikipedia_url = breed.getWikipedia_url();
        binding.textViewWikipediaBreedFragment.setText(wikipedia_url);
        binding.ratingBarIndoor.setRating(breed.getIndoor());
        binding.ratingBarAdaptability.setRating(breed.getAdaptability());
        binding.ratingBaraAffectionLevel.setRating(breed.getAffection_level());
        binding.ratingBarChildFriendly.setRating(breed.getChild_friendly());
        binding.ratingBarCatFriendly.setRating(breed.getCat_friendly());
        binding.ratingBarDogFriendly.setRating(breed.getDog_friendly());
        binding.ratingBarEnergyLevel.setRating(breed.getEnergy_level());
        binding.ratingBarGrooming.setRating(breed.getGrooming());
        binding.ratingBarHealthIssues.setRating(breed.getHealth_issues());
        binding.ratingBarIntelligence.setRating(breed.getIntelligence());
        binding.ratingBarSheddingLevel.setRating(breed.getShedding_level());
        binding.ratingBarSocialNeeds.setRating(breed.getSocial_needs());
        binding.ratingBarStrangerFriendly.setRating(breed.getStranger_friendly());
        binding.ratingBarVocalisation.setRating(breed.getVocalisation());
        binding.ratingBarExperimental.setRating(breed.getExperimental());
        binding.ratingBarHairless.setRating(breed.getHairless());
        binding.ratingBarNatural.setRating(breed.getNatural());
        binding.ratingBarRare.setRating(breed.getRare());
        binding.ratingBarRex.setRating(breed.getRex());
        binding.ratingBarSuppressedTail.setRating(breed.getSuppressed_tail());
        binding.ratingBarShortLegs.setRating(breed.getShort_legs());


    }





    private void setValuesDogViews(DogResponse dogResponse){
        try {
            Picasso.get().load(dogResponse.getUrl()).into(binding.imageViewBreedFragment);
        } catch (Exception e) {
            Picasso.get().load(R.drawable.goback).into(binding.imageViewBreedFragment);
            e.printStackTrace();
        }
        List<BreedsDog> listBreedsDog = dogResponse.getBreeds();
        BreedsDog breedsDog = listBreedsDog.get(0);
        String content = breedsDog.getName() + "\n";
        content += breedsDog.getBred_for() + "\n";
        content += breedsDog.getBreed_group() + "\n";
        content += breedsDog.getLife_span() + "\n";
        content += breedsDog.getTemperament() + "\n";
        content += breedsDog.getWeight().getMetric() + "\n";
        content += breedsDog.getHeight().getMetric() + "\n";
        binding.textViewBreedFragment.setText(content);
        binding.textViewWikipediaBreedFragment.setMovementMethod(LinkMovementMethod.getInstance());
        String breedName = breedsDog.getName().replace(" ", "%20");
        String wikipedia_url = "https://es.wikipedia.org/w/index.php?search=" + breedName;
        binding.textViewWikipediaBreedFragment.setText(wikipedia_url);
        binding.ratingsBar.setVisibility(View.GONE);

    }
}