package com.example.recipe_finder.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_finder.model.Category;
import com.example.recipe_finder.repository.CategoryRepository;
import com.example.recipe_finder.repository.CategoryRepositoryImpl;

import java.util.ArrayList;

public class CategoriesViewModel extends ViewModel {
    CategoryRepository categoryRepository;

    public CategoriesViewModel() {
        this.categoryRepository = CategoryRepositoryImpl.getInstance();
    }

    public LiveData<ArrayList<Category>> getCategories() {
        return categoryRepository.getCategories();
    }
}
