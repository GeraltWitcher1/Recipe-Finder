package com.example.recipe_finder.networking.responses;

import androidx.annotation.NonNull;

import com.example.recipe_finder.model.RecipeListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecipeListResponse {
    public ArrayList<RecipeListItem> meals;


    @NonNull
    @Override
    public String toString() {
        return Arrays.toString(new List[]{Collections.singletonList(meals)});
    }
}
