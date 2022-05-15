package com.example.recipe_finder.ui.recipe_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.recipe_finder.ui.recipe.RecipeViewModel;
import com.example.recipe_finder.utility.RecipeListAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeListFragment extends Fragment implements RecipeListAdapter.RecipeOnClickListener {

    RecyclerView recipeList;
    RecipeListAdapter adapter;

    RecipeListViewModel recipeListViewModel;
    RecipeViewModel recipeViewModel;

    TextView noRecipesFoundLabel;

    private ArrayList<RecipeListItem> recipes;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeList = view.findViewById(R.id.recipe_recycler_view);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));

        recipes = new ArrayList<>();
        adapter = new RecipeListAdapter(recipes, this);
        recipeList.setAdapter(adapter);
        noRecipesFoundLabel = view.findViewById(R.id.no_recipes_found);

        recipes.clear();
        recipeListViewModel = new ViewModelProvider(requireActivity()).get(RecipeListViewModel.class);
        recipeListViewModel.getRecipes().observe(getViewLifecycleOwner(), this::addRecipes);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        if (getArguments() != null && getArguments().get("NavigationSource") != null) {
            recipeListViewModel.updateRecipesFavourite();
        }

    }

    @SuppressLint("SetTextI18n")
    private void addRecipes(ArrayList<RecipeListItem> recipeListItems) {
        recipes.clear();
        if (recipeListItems == null || recipeListItems.size()==0) {
            noRecipesFoundLabel.setVisibility(View.VISIBLE);
        } else {
            noRecipesFoundLabel.setVisibility(View.GONE);
            recipes.addAll(recipeListItems);
            adapter.setRecipes(recipes);
        }
    }


    @Override
    public void onClick(RecipeListItem recipe) {
        Bundle bundle = new Bundle();
        bundle.putInt("RecipeId", recipe.getIdMeal());
        NavHostFragment.findNavController(this).navigate(R.id.nav_recipe, bundle);
    }
}
