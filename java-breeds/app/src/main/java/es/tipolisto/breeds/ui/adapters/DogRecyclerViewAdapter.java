package es.tipolisto.breeds.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.DogResponse;

public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogRecyclerViewAdapter.ViewHolder> {
    private List<DogResponse> listDogResponse;
    public DogRecyclerViewAdapter(List<DogResponse> listDogResponse){
        this.listDogResponse=listDogResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(listDogResponse.get(position));
    }

    @Override
    public int getItemCount() {
        return listDogResponse.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageViewRecyclerView);
            textView=(TextView) itemView.findViewById(R.id.textViewRecyclerView);
        }
        public void render(DogResponse dogResponse){
            try{
                Picasso.get().load(dogResponse.getUrl()).into(imageView);
            }catch (Exception ex){
                Picasso.get().load(R.drawable.goback).into(imageView);
            }
            if(dogResponse.getBreeds().size()>=1){
                BreedsDog breedsDog= dogResponse.getBreeds().get(0);
                textView.setText(breedsDog.getName());
            }

        }
    }
}
