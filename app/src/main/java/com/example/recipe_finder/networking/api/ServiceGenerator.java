package com.example.recipe_finder.networking.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static FoodAPI foodAPI;

    public static FoodAPI getFoodAPI() {
        if (foodAPI == null) {
            foodAPI = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FoodAPI.class);
        }
        return foodAPI;
    }

}
