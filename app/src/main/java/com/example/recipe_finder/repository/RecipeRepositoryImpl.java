package com.example.recipe_finder.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.networking.api.FoodAPI;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.RecipeListResponse;
import com.example.recipe_finder.utility.RecipeApiToModelMapper;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeRepositoryImpl implements RecipeRepository {

    private static RecipeRepository instance;

    private final MutableLiveData<ArrayList<RecipeListItem>> recipes;

    private final MutableLiveData<Recipe> recipe;


    private RecipeRepositoryImpl() {
        recipes = new MutableLiveData<>();
        recipe = new MutableLiveData<>();
    }

    public static synchronized RecipeRepository getInstance() {
        if (instance == null)
            instance = new RecipeRepositoryImpl();
        return instance;
    }

    public LiveData<ArrayList<RecipeListItem>> getRecipes() {
        return this.recipes;
    }

    public LiveData<Recipe> getRecipe(){
        return this.recipe;
    }

    public void updateRecipeById(String id){
        findRecipeById(id);
    }

    @Override
    public void updateRecipeRandom() {
        findRandomRecipe();
    }


    @Override
    public void updateListBySearch(String recipeName) {
        findRecipesBySearch(recipeName);
    }

    @Override
    public void updateListByCategory(String categoryName) {
        findRecipesByCategory(categoryName);
    }

    @Override
    public void updateListByCuisine(String cuisineName) {
        findRecipeByCuisine(cuisineName);
    }


    private void findRandomRecipe(){
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<JsonObject> call = foodAPI.getRandomRecipe();

        call.enqueue(new Callback<JsonObject>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    recipe.setValue(RecipeApiToModelMapper.map(Objects.requireNonNull(response.body())));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });
    }

    private void findRecipeById(String id){
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<JsonObject> call = foodAPI.getRecipeById(id);

        call.enqueue(new Callback<JsonObject>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    recipe.setValue(RecipeApiToModelMapper.map(Objects.requireNonNull(response.body())));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });

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

    private void findRecipeByCuisine(String cuisineName) {
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<RecipeListResponse> call = foodAPI.getRecipesByCuisine(cuisineName);

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
