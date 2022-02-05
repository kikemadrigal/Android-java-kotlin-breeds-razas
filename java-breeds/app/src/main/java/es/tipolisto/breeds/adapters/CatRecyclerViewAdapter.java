package es.tipolisto.breeds.adapters;

import android.util.Log;
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
import es.tipolisto.breeds.model.Cat;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.ViewHolder> {

    List<Cat> catList;
    public CatRecyclerViewAdapter(List<Cat> catList){
        this.catList=catList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null, false);
        //bindind = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(catList.get(position));
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
            imageView=(ImageView) view.findViewById(R.id.imageViewRecyclerView);
            TexView=(TextView) view.findViewById(R.id.textViewRecyclerView);
        }
        public void render(Cat cat){
            try {
                Picasso.get().load(cat.getImage().getUrl()).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TexView.setText(cat.getName());
            Log.d("Mensaje","Mensaje de adapter: "+cat.getName());
        }

    }
}
