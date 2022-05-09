package com.example.recipe_finder.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.repository.RecipeRepository;
import com.example.recipe_finder.repository.RecipeRepositoryImpl;

public class RecipeViewModel extends ViewModel {

    RecipeRepository recipeRepository;

    public RecipeViewModel() {
        this.recipeRepository = RecipeRepositoryImpl.getInstance();
    }

    public LiveData<Recipe> getRecipe() {
        return recipeRepository.getRecipe();
    }

    public void updateRecipeRandom(){
        recipeRepository.updateRecipeRandom();
    }

    public void updateRecipeById(String id){
        recipeRepository.updateRecipeById(id);
    }
}
