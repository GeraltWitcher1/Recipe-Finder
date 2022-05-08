package com.example.recipe_finder.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe_finder.model.Category;
import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.networking.api.FoodAPI;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.CategoryListResponse;
import com.example.recipe_finder.networking.responses.RecipeListResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static CategoryRepository instance;

    private MutableLiveData<ArrayList<Category>> categories;


    private CategoryRepositoryImpl() {
        categories = new MutableLiveData<>();
    }

    public static synchronized CategoryRepository getInstance() {
        if (instance == null)
            instance = new CategoryRepositoryImpl();
        return instance;

    }

    @Override
    public LiveData<ArrayList<Category>> getCategories() {
        getCategoriesFromAPI();
        return categories;
    }

    private void getCategoriesFromAPI() {
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<CategoryListResponse> call = foodAPI.getCategories();

        call.enqueue(new Callback<CategoryListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {
                if (response.isSuccessful()) {
                    categories.setValue(Objects.requireNonNull(response.body()).meals);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });
    }
}
