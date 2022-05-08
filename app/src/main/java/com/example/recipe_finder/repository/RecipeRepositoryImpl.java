package com.example.recipe_finder.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.networking.api.FoodAPI;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.RecipeListResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeRepositoryImpl implements RecipeRepository {

    private static RecipeRepository instance;

    private MutableLiveData<ArrayList<RecipeListItem>> recipes;


    private RecipeRepositoryImpl() {
        recipes = new MutableLiveData<>();
    }

    public static synchronized RecipeRepository getInstance() {
        if (instance == null)
            instance = new RecipeRepositoryImpl();
        return instance;

    }

    public LiveData<ArrayList<RecipeListItem>> getRecipes() {
        return this.recipes;
    }

    public void updateBySearch(String recipeName) {
        findRecipesBySearch(recipeName);
    }

    public void updateByCategory(String categoryName) {
        findRecipesByCategory(categoryName);
    }

    @Override
    public void updateByCuisine(String cuisineName) {

    }

    private void findRecipesBySearch(String recipeName) {
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<RecipeListResponse> call = foodAPI.getRecipeListItems(recipeName);

        call.enqueue(new Callback<RecipeListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RecipeListResponse> call, Response<RecipeListResponse> response) {
                if (response.isSuccessful()) {
                    recipes.setValue(Objects.requireNonNull(response.body()).meals);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RecipeListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });
    }

    private void findRecipesByCategory(String categoryName) {
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<RecipeListResponse> call = foodAPI.getRecipesByCategory(categoryName);

        call.enqueue(new Callback<RecipeListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RecipeListResponse> call, Response<RecipeListResponse> response) {
                if (response.isSuccessful()) {
                    recipes.setValue(Objects.requireNonNull(response.body()).meals);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RecipeListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });
    }




}
