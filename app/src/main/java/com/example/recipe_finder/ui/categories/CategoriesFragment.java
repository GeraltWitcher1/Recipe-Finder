package com.example.recipe_finder.ui.categories;

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
import com.example.recipe_finder.model.Category;
import com.example.recipe_finder.ui.recipe_list.RecipeListViewModel;
import com.example.recipe_finder.utility.CategoriesAdapter;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategoryOnClickListener {

    RecyclerView categoryList;
    CategoriesAdapter adapter;

    CategoriesViewModel categoriesViewModel;
    RecipeListViewModel recipeListViewModel;

    private ArrayList<Category> categories;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryList = view.findViewById(R.id.recipe_recycler_view);
        categoryList.hasFixedSize();
        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        categories = new ArrayList<>();
        adapter = new CategoriesAdapter(categories, this);
        categoryList.setAdapter(adapter);

        categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoriesViewModel.getCategories().observe(getViewLifecycleOwner(), this::addCategories);

        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

    }

    private void addCategories(ArrayList<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        adapter.setCategories(this.categories);
    }

    @Override
    public void onClick(Category category) {
        recipeListViewModel.updateRecipesByCategory(category.getStrCategory());
        NavHostFragment.findNavController(this).navigate(R.id.nav_recipe_list);
    }
}
