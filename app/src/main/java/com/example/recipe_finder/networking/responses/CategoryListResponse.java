package com.example.recipe_finder.networking.responses;

import androidx.annotation.NonNull;


import com.example.recipe_finder.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CategoryListResponse {

    public ArrayList<Category> meals;

    @NonNull
    @Override
    public String toString() {
        return Arrays.toString(new List[]{Collections.singletonList(meals)});
    }

}
