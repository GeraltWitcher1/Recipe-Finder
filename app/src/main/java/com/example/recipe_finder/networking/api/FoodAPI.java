package com.example.recipe_finder.networking.api;

import com.example.recipe_finder.networking.responses.RecipeListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodAPI {


    @GET("search.php")
    Call<RecipeListResponse> getRecipeListItems(@Query("s") String name);

}
