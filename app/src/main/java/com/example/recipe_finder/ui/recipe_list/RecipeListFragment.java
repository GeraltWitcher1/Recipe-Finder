package com.example.recipe_finder.ui.recipe_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.utility.RecipeListAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class RecipeListFragment extends Fragment implements RecipeListAdapter.RecipeOnClickListener {

    RecyclerView recipeList;
    RecipeListAdapter adapter;

    RecipeListViewModel viewModel;

    private ArrayList<RecipeListItem> recipes;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeList = view.findViewById(R.id.recipeRecyclerView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));

        recipes = new ArrayList<>();
        adapter = new RecipeListAdapter(recipes, this);
        recipeList.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), this::addRecipes);

    }

    private void addRecipes(ArrayList<RecipeListItem> recipeListItems) {
        recipes.clear();
        recipes.addAll(recipeListItems);
        adapter.setRecipes(recipes);
    }


    @Override
    public void onClick(RecipeListItem recipe) {
        Toast.makeText(getContext(),recipe.getIdMeal()+ " "+recipe.getStrMeal(), Toast.LENGTH_LONG).show();
    }
}
