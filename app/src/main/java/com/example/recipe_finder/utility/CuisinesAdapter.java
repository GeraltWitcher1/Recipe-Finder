package com.example.recipe_finder.utility;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.Cuisine;


import java.util.ArrayList;

public class CuisinesAdapter extends RecyclerView.Adapter<CuisinesAdapter.ViewHolder> {

    private ArrayList<Cuisine> cuisines;
    private final CuisineOnClickListener cuisineListener;

    public CuisinesAdapter(ArrayList<Cuisine> cuisines, CuisineOnClickListener cuisineListener) {
        this.cuisines = cuisines;
        this.cuisineListener = cuisineListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_cuisine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cuisine item = cuisines.get(position);

        holder.name.setText(item.getStrArea());
    }

    @Override
    public int getItemCount() {
        return cuisines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> cuisineListener.onClick(cuisines.get(getAdapterPosition())));

            name = itemView.findViewById(R.id.item_name);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCuisines(ArrayList<Cuisine> cuisines) {
        this.cuisines = cuisines;
        notifyDataSetChanged();
    }

    public interface CuisineOnClickListener {
        void onClick(Cuisine cuisine);
    }
}
