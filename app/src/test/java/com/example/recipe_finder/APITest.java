package com.example.recipe_finder;

import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.RecipeListResponse;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import retrofit2.Call;

public class APITest {

    @Test
    public void testFoodAPI() throws IOException {
        Call<RecipeListResponse> call = ServiceGenerator.getFoodAPI().getRecipeListItems("Carbonara");
        System.out.println(call.toString());
        retrofit2.Response<RecipeListResponse> response = call.execute();

        assertNotNull(response.body());
        System.out.println(response.body());

        assertEquals(200,response.code());



    }
}
