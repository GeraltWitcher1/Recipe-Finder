package com.example.recipe_finder.ui.recipe_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.repository.RecipeRepository;
import com.example.recipe_finder.repository.RecipeRepositoryImpl;

import java.util.ArrayList;

public class RecipeListViewModel extends ViewModel {

    RecipeRepository recipeRepository;

    public RecipeListViewModel() {
        this.recipeRepository = RecipeRepositoryImpl.getInstance();
    }

    public LiveData<ArrayList<RecipeListItem>> getRecipes() {
        return recipeRepository.getRecipes();
    }

    public void updateRecipesFromSearch(String searchString){
        recipeRepository.updateListBySearch(searchString);
    }

    public void updateRecipesByCategory(String categoryName){
        recipeRepository.updateListByCategory(categoryName);
    }

    public void updateRecipesByCuisine(String cuisineName){

    }

}
