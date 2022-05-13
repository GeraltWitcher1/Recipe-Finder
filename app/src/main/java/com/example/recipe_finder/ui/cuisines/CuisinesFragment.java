package com.example.recipe_finder.ui.cuisines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.Cuisine;
import com.example.recipe_finder.ui.recipe_list.RecipeListViewModel;
import com.example.recipe_finder.utility.CuisinesAdapter;

import java.util.ArrayList;

public class CuisinesFragment extends Fragment implements CuisinesAdapter.CuisineOnClickListener{

    RecyclerView cuisineList;

    CuisinesViewModel cuisinesViewModel;
    RecipeListViewModel recipeListViewModel;

    private CuisinesAdapter adapter;

    private ArrayList<Cuisine> cuisines;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cuisines, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cuisineList = view.findViewById(R.id.recipe_recycler_view);
        cuisineList.hasFixedSize();
        cuisineList.setLayoutManager(new LinearLayoutManager(getContext()));

        cuisines = new ArrayList<>();
        adapter = new CuisinesAdapter(cuisines, this);
        cuisineList.setAdapter(adapter);

        cuisinesViewModel = new ViewModelProvider(this).get(CuisinesViewModel.class);
        cuisinesViewModel.getCategories().observe(getViewLifecycleOwner(), this::addCuisines);

        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

    }

    private void addCuisines(ArrayList<Cuisine> cuisines) {
        this.cuisines.clear();
        this.cuisines.addAll(cuisines);
        adapter.setCuisines(this.cuisines);
    }

    @Override
    public void onClick(Cuisine cuisine) {
        recipeListViewModel.updateRecipesByCuisine(cuisine.getStrArea());
        NavHostFragment.findNavController(this).navigate(R.id.nav_recipe_list);
    }
}
