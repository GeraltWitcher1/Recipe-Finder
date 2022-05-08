package com.example.recipe_finder.ui.recipe_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.repository.RecipeRepository;
import com.example.recipe_finder.repository.RecipeRepositoryImpl;

import java.util.ArrayList;

public class RecipeListViewModel extends ViewModel {

    RecipeRepository recipeRepository;

    RecipeListItem currentRecipe;

    public RecipeListViewModel() {
        this.recipeRepository = RecipeRepositoryImpl.getInstance();
    }

    public LiveData<ArrayList<RecipeListItem>> getRecipes() {
        return recipeRepository.getRecipes();
    }

    public void updateRecipesFromSearch(String searchString){
        recipeRepository.updateBySearch(searchString);
    }

    public void updateRecipesByCategory(String categoryName){
        recipeRepository.updateByCategory(categoryName);
    }

    public void updateRecipesByCuisine(String cuisineName){

    }

    public void setCurrentRecipe(RecipeListItem currentRecipe) {
        this.currentRecipe = currentRecipe;
    }
}
