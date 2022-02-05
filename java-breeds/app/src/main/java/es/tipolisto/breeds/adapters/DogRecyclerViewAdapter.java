package es.tipolisto.breeds.adapters;

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

public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogRecyclerViewAdapter.ViewHolder>{
    private List<String> listDogImages;

    public DogRecyclerViewAdapter(List<String> listDogImages){
        this.listDogImages=listDogImages;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(listDogImages.get(position));
    }

    @Override
    public int getItemCount() {
        return listDogImages.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageViewRecyclerView);
            textView=(TextView) itemView.findViewById(R.id.textViewRecyclerView);
        }
        public void render(String dogImage){
            try{
                Picasso.get().load(dogImage).into(imageView);
            }catch (Exception ex){

            }
            //Obtenemos el nombre de la raza que está en la URL
            int posicion4barra=dogImage.indexOf("/",25);
            int posicion5Barra=dogImage.indexOf("/",32);
            String breed_name=dogImage.substring(posicion4barra+1,posicion5Barra);
            textView.setText(breed_name);
        }
    }
}
