package com.example.recipe_finder.networking.api;

import com.example.recipe_finder.networking.responses.CategoryListResponse;
import com.example.recipe_finder.networking.responses.RecipeListResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodAPI {


    @GET("search.php")
    Call<RecipeListResponse> getRecipeListItems(@Query("s") String name);

    @GET("list.php?c=list")
    Call<CategoryListResponse> getCategories();

    @GET("filter.php")
    Call<RecipeListResponse> getRecipesByCategory(@Query("c") String categoryName);

    @GET("random.php")
    Call<JsonObject> getRandomRecipe();

    @GET("lookup.php")
    Call<JsonObject> getRecipeById(@Query("i")String recipeId);
}
