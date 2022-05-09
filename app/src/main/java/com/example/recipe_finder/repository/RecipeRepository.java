package com.example.recipe_finder.repository;

import androidx.lifecycle.LiveData;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.model.RecipeListItem;

import java.util.ArrayList;

public interface RecipeRepository {

    LiveData<ArrayList<RecipeListItem>> getRecipes();

    LiveData<Recipe> getRecipe();

    void updateListBySearch(String recipeName);

    void updateListByCategory(String categoryName);

    void updateListByCuisine(String cuisineName);

    void updateRecipeById(String id);

    void updateRecipeRandom();
}
