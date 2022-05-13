package com.example.recipe_finder.ui.cuisines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.Cuisine;
import com.example.recipe_finder.repository.CuisineRepository;
import com.example.recipe_finder.repository.CuisineRepositoryImpl;

import java.util.ArrayList;

public class CuisinesViewModel extends ViewModel {
    CuisineRepository cuisineRepository;

    public CuisinesViewModel() {
        this.cuisineRepository = CuisineRepositoryImpl.getInstance();
    }

    public LiveData<ArrayList<Cuisine>> getCategories() {
        return cuisineRepository.getCuisines();
    }
}
