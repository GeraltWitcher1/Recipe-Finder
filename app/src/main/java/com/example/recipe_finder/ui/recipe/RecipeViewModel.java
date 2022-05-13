package com.example.recipe_finder.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.repository.RecipeRepository;
import com.example.recipe_finder.repository.RecipeRepositoryImpl;

public class RecipeViewModel extends ViewModel {

    RecipeRepository recipeRepository;

    String videoURL;
    String recipeName;


    public RecipeViewModel() {
        this.recipeRepository = RecipeRepositoryImpl.getInstance();
        this.videoURL = "No video link :(";
    }

    public LiveData<Recipe> getRecipe() {
        return recipeRepository.getRecipe();
    }

    public void updateRecipeRandom() {
        recipeRepository.updateRecipeRandom();
    }

    public void updateRecipeById(String id) {
        recipeRepository.updateRecipeById(id);
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
