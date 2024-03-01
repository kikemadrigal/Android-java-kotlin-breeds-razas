package es.tipolisto.breeds.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.databinding.FragmentBreedBinding;


import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.ui.viewmodels.BreedFragmentViewModel;


public class BreedDetailFragment extends Fragment {

    private FragmentBreedBinding binding;
    private static final String ARG_PARAM1 = "breed";
    private static final String ARG_PARAM2= "modo";
    private String breedName;
    private String mode;


    public BreedDetailFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            breedName = getArguments().getString(ARG_PARAM1);
            mode = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentBreedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BreedFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(BreedFragmentViewModel.class);
        viewModel.getMutableLiveDataProgressBarVisible().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                binding.progressBar.setVisibility(View.VISIBLE);
            else
                binding.progressBar.setVisibility(View.GONE);
        });
        if (getArguments() != null) {
            if (mode.equals("cat")) {
                viewModel.getCatBynameFromBuffer(breedName);
                //viewModel.getMutableLiveDataCat().observe(getViewLifecycleOwner(), cat -> setValuesCatViews(cat));
                viewModel.getMutableLiveDataCat().observe(getViewLifecycleOwner(), this::setValuesCatViews);
            } else if (mode.equals("dog")) {
                viewModel.getDogBynameFromBuffer(breedName);
                //viewModel.getMutableLiveDataDog().observe(getViewLifecycleOwner(), dog -> setValuesDogViews(dog));
                viewModel.getMutableLiveDataDog().observe(getViewLifecycleOwner(), this::setValuesDogViews);
            }
            binding.textViewBreedFragment.setText(breedName);
        }
    }



    private void setValuesCatViews(Cat cat) {
        try {
            Picasso.get().load(cat.getImage().getUrl()).into(binding.imageViewBreedFragment);
        } catch (Exception e) {
            Picasso.get().load(R.drawable.goback).into(binding.imageViewBreedFragment);
            e.printStackTrace();
        }
        String content = cat.getName() + "\n";
        content += cat.getTemperament() + "\n";
        content += cat.getOrigin() + "\n";
        content += cat.getCountry_code() + "\n";
        content += cat.getDescription() + "\n";
        binding.textViewBreedFragment.setText(content);
        binding.textViewWikipediaBreedFragment.setMovementMethod(LinkMovementMethod.getInstance());
        String wikipedia_url = cat.getWikipedia_url();
        binding.textViewWikipediaBreedFragment.setText(wikipedia_url);
        binding.ratingBarIndoor.setRating(cat.getIndoor());
        binding.ratingBarAdaptability.setRating(cat.getAdaptability());
        binding.ratingBaraAffectionLevel.setRating(cat.getAffection_level());
        binding.ratingBarChildFriendly.setRating(cat.getChild_friendly());
        binding.ratingBarChildFriendly.setRating(cat.getCat_friendly());
        binding.ratingBarDogFriendly.setRating(cat.getDog_friendly());
        binding.ratingBarEnergyLevel.setRating(cat.getEnergy_level());
        binding.ratingBarGrooming.setRating(cat.getGrooming());
        binding.ratingBarHealthIssues.setRating(cat.getHealth_issues());
        binding.ratingBarIntelligence.setRating(cat.getIntelligence());
        binding.ratingBarSheddingLevel.setRating(cat.getShedding_level());
        binding.ratingBarSocialNeeds.setRating(cat.getSocial_needs());
        binding.ratingBarStrangerFriendly.setRating(cat.getStranger_friendly());
        binding.ratingBarVocalisation.setRating(cat.getVocalisation());
        binding.ratingBarExperimental.setRating(cat.getExperimental());
        binding.ratingBarHairless.setRating(cat.getHairless());
        binding.ratingBarNatural.setRating(cat.getNatural());
        binding.ratingBarRare.setRating(cat.getRare());
        binding.ratingBarRex.setRating(cat.getRex());
        binding.ratingBarSuppressedTail.setRating(cat.getSuppressed_tail());
        binding.ratingBarShortLegs.setRating(cat.getShort_legs());


    }





    private void setValuesDogViews(BreedsDog breedsDog){
        try {
            Picasso.get().load(breedsDog.getImage().getUrl()).into(binding.imageViewBreedFragment);
        } catch (Exception e) {
            Picasso.get().load(R.drawable.goback).into(binding.imageViewBreedFragment);
            e.printStackTrace();
        }

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