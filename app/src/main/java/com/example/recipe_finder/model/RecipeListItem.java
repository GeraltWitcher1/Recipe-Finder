package com.example.recipe_finder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecipeListItem {

    @PrimaryKey
    private int idMeal;
    private String strMeal;
    private String strMealThumb;

    public int getIdMeal() {
        return idMeal;
    }

    public RecipeListItem(int idMeal, String strMeal, String strMealThumb) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecipeListItem{" +
                "idMeal=" + idMeal +
                ", strMeal='" + strMeal + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                '}';
    }
}
