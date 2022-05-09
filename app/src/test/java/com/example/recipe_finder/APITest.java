package com.example.recipe_finder;

import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.CategoryListResponse;
import com.example.recipe_finder.networking.responses.RecipeListResponse;
import com.example.recipe_finder.utility.RecipeApiToModelMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;

public class APITest {

    @Test
    public void testRecipeList() throws IOException {
        Call<RecipeListResponse> call = ServiceGenerator.getFoodAPI().getRecipeListItems("Carbonara");
        System.out.println(call.toString());
        retrofit2.Response<RecipeListResponse> response = call.execute();

        assertNotNull(response.body());
        System.out.println(response.body());

        assertEquals(200, response.code());
    }

    @Test
    public void testCategories() throws IOException {
        Call<CategoryListResponse> call = ServiceGenerator.getFoodAPI().getCategories();
        System.out.println(call.toString());
        retrofit2.Response<CategoryListResponse> response = call.execute();

        assertNotNull(response.body());
        System.out.println(response.body());

        assertEquals(200, response.code());
    }

    @Test
    public void testFoodByCategory() throws IOException {
        Call<RecipeListResponse> call = ServiceGenerator.getFoodAPI().getRecipesByCategory("Seafood");
        System.out.println(call.toString());
        retrofit2.Response<RecipeListResponse> response = call.execute();

        assertNotNull(response.body());
        System.out.println(response.body());

        assertEquals(200, response.code());
    }

    @Test
    public void testRandomRecipe() throws IOException {
        Call<JsonObject> call = ServiceGenerator.getFoodAPI().getRandomRecipe();
        System.out.println(call.toString());
        retrofit2.Response<JsonObject> response = call.execute();


        assertNotNull(response.body());
        System.out.println(response.body());

        assertEquals(200, response.code());
    }

    @Test
    public void testRecipeMapping() throws IOException {
        Call<JsonObject> call = ServiceGenerator.getFoodAPI().getRecipeById("52772");
        retrofit2.Response<JsonObject> response = call.execute();

        Gson gson = new Gson();

        JsonObject jsonObject = null;

        if (response.body() != null) {
            jsonObject = gson.fromJson(response.body().toString(), JsonObject.class);
        }
        assert jsonObject != null;
        Recipe recipe = RecipeApiToModelMapper.map(jsonObject);

        assertEquals(recipe.getName(),"Teriyaki Chicken Casserole");


    }


}
