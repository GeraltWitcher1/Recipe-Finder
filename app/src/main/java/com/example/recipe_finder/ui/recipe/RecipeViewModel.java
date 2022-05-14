package com.example.recipe_finder.ui.recipe;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.repository.RecipeRepository;
import com.example.recipe_finder.repository.RecipeRepositoryImpl;

public class RecipeViewModel extends AndroidViewModel {

    RecipeRepository recipeRepository;

    Recipe recipe;


    public RecipeViewModel(Application app) {
        super(app);
        this.recipeRepository = RecipeRepositoryImpl.getInstance(app);
    }

    public LiveData<Recipe> observeRecipe() {
        return recipeRepository.getRecipe();
    }

    public void updateRecipeRandom() {
        recipeRepository.updateRecipeRandom();
    }

    public void updateRecipeById(String id) {
        recipeRepository.updateRecipeById(id);
    }

    public void toggleFavouriteButton(){
        recipeRepository.toggleFavourite(recipe);
    }


    public Recipe getRecipe(){
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
