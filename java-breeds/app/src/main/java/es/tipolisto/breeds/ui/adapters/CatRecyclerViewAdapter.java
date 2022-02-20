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
import es.tipolisto.breeds.data.model.Cat;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.ViewHolder> {

    List<Cat> catList;
    public CatRecyclerViewAdapter(List<Cat> catList){
        this.catList=catList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(catList.get(position));
        //holder.render(catList.get(position));
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView TexView;
        public ViewHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.imageViewRecyclerView);
            TexView=view.findViewById(R.id.textViewRecyclerView);
        }
        public void render(Cat catListResponse){
            try {
                Picasso.get().load(catListResponse.getImage().getUrl()).into(imageView);
            } catch (Exception e) {
                Picasso.get().load(R.drawable.goback).into(imageView);
                e.printStackTrace();
            }
            TexView.setText(catListResponse.getName());
            //Log.d("Mensaje","Mensaje de adapter: "+catListResponse.getName());
        }

    }
}
