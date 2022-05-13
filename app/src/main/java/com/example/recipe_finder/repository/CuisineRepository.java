package com.example.recipe_finder.repository;

import androidx.lifecycle.LiveData;

import com.example.recipe_finder.model.Cuisine;

import java.util.ArrayList;

public interface CuisineRepository {
    LiveData<ArrayList<Cuisine>> getCuisines();
}
