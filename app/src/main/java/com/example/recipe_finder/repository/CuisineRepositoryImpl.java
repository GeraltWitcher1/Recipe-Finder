package com.example.recipe_finder.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe_finder.model.Cuisine;
import com.example.recipe_finder.networking.api.FoodAPI;
import com.example.recipe_finder.networking.api.ServiceGenerator;
import com.example.recipe_finder.networking.responses.CuisineListResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CuisineRepositoryImpl implements CuisineRepository {

    private static CuisineRepository instance;

    private MutableLiveData<ArrayList<Cuisine>> cuisines;


    private CuisineRepositoryImpl() {
        cuisines = new MutableLiveData<>();
    }

    public static synchronized CuisineRepository getInstance() {
        if (instance == null)
            instance = new CuisineRepositoryImpl();
        return instance;

    }

    @Override
    public LiveData<ArrayList<Cuisine>> getCuisines() {
        getCategoriesFromAPI();
        return cuisines;
    }

    private void getCategoriesFromAPI() {
        FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
        Call<CuisineListResponse> call = foodAPI.getCuisines();

        call.enqueue(new Callback<CuisineListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<CuisineListResponse> call, Response<CuisineListResponse> response) {
                if (response.isSuccessful()) {
                    cuisines.setValue(Objects.requireNonNull(response.body()).meals);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<CuisineListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t);
            }
        });
    }
}
