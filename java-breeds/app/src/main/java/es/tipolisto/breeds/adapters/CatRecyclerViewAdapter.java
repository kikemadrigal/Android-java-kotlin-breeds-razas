package es.tipolisto.breeds.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.databinding.ItemRecyclerViewBinding;
import es.tipolisto.breeds.model.Cat;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.ViewHolder> {

    List<Cat> catList;
    public CatRecyclerViewAdapter(List<Cat> catList){
        this.catList=catList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylcer_view_card, null, false);
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
        TextView textViewId,TexViewName,textViewUrl;
        public ViewHolder(View view){
            super(view);
            imageView=(ImageView) view.findViewById(R.id.imageViewRecyclerView);
            TexViewName=(TextView) view.findViewById(R.id.textViewNameRecyclerView);
        }
        public void render(Cat cat){
            try {
                Picasso.get().load(cat.getImage().getUrl()).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TexViewName.setText(cat.getName());
        }

    }
}
