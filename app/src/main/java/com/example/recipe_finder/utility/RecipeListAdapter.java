package com.example.recipe_finder.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.RecipeListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private ArrayList<RecipeListItem> recipes;

    public RecipeListAdapter(ArrayList<RecipeListItem> recipes) {
        System.out.println("Entering recipeList");
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecipeListItem item = recipes.get(position);

        holder.name.setText(item.getName());
        System.out.println(recipes.get(position).getThumbURL());
        Picasso.get().setLoggingEnabled(true);
        Picasso.get()
                .load(item.getThumbURL())
                .fit()
                .centerCrop()
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name);
            thumbnail = itemView.findViewById(R.id.recipe_image);
        }
    }

//    public interface OnClickListener {
//        void onClick(RecipeListItem recipe);
//    }
}
