package com.example.recipe_finder.repository;

import androidx.lifecycle.LiveData;

import com.example.recipe_finder.model.Category;

import java.util.ArrayList;

public interface CategoryRepository {
    LiveData<ArrayList<Category>> getCategories();
}
