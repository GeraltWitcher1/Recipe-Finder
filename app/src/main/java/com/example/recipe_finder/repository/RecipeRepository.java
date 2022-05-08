package com.example.recipe_finder.repository;

import androidx.lifecycle.LiveData;

import com.example.recipe_finder.model.RecipeListItem;

import java.util.ArrayList;

public interface RecipeRepository {
    LiveData<ArrayList<RecipeListItem>> getRecipes();

    void updateBySearch(String recipeName);

    void updateByCategory(String categoryName);

    void updateByCuisine(String cuisineName);
}
