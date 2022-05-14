package com.example.recipe_finder.repository;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe_finder.database.RecipeDAO;
import com.example.recipe_finder.database.RecipeDatabase;
import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.networking.api.FoodAPI;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.RecipeListResponse;
import com.example.recipe_finder.utility.RecipeApiToModelMapper;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeRepositoryImpl implements RecipeRepository {

    private static RecipeRepository instance;

    private final MutableLiveData<ArrayList<RecipeListItem>> recipes;

    private final MutableLiveData<Recipe> recipe;

    private FoodAPI foodAPI;

    private RecipeDAO recipeDAO;

    private final ExecutorService executorService;

    private ConnectivityManager connectivityManager;

    private RecipeRepositoryImpl(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipes = new MutableLiveData<>();
        recipe = new MutableLiveData<>();
        foodAPI = ServiceGenerator.getFoodAPI();
        recipeDAO = database.recipeDAO();
        executorService = Executors.newFixedThreadPool(2);
        connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static synchronized RecipeRepository getInstance(Application application) {
        if (instance == null)
            instance = new RecipeRepositoryImpl(application);
        return instance;
    }

    public LiveData<ArrayList<RecipeListItem>> getRecipes() {
        return this.recipes;
    }

    public LiveData<Recipe> getRecipe() {
        return this.recipe;
    }

    public void updateRecipeById(String id) {
        findRecipeById(id);
    }

    @Override
    public void updateRecipeRandom() {
        findRandomRecipe();
    }

    @Override
    public void updateListFavourites() {
        findFavouriteRecipes();
    }

    @Override
    public void toggleFavourite(Recipe recipe) {

        executorService.execute(() -> {
            if (recipeDAO.exists(recipe.getId())) {
                System.out.println("Removing from favourite");
                recipeDAO.delete(recipe);
            } else {
                recipeDAO.insert(recipe);
                System.out.println("adding to favorite");
            }
        });

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
        findRecipesByCuisine(cuisineName);
    }

    private void findRandomRecipe() {
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

    private void findRecipeById(String id) {
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

    private void findRecipesByCuisine(String cuisineName) {
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

    private void findFavouriteRecipes() {
        ArrayList<RecipeListItem> favouriteRecipes = new ArrayList<>();

        executorService.execute(()->{
            for (Recipe recipe : recipeDAO.getAllFavourites()) {
                favouriteRecipes.add(new RecipeListItem(recipe.getId(), recipe.getName(), recipe.getImage()));
            }
            recipes.postValue(favouriteRecipes);
        });

    }

    private boolean isInternetAvailable() {
        return connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
