package com.example.recipe_finder;

import androidx.lifecycle.LiveData;

import com.example.recipe_finder.model.Category;
import com.example.recipe_finder.repository.CategoryRepository;
import com.example.recipe_finder.repository.CategoryRepositoryImpl;

import org.junit.Test;

import java.util.ArrayList;

public class TestCategoryRepository {
    @Test
    public void testCategories(){
        CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

        LiveData<ArrayList<Category>> categoryArrayList = categoryRepository.getCategories();

        System.out.println(categoryArrayList.getValue());
    }
}
