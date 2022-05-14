package com.example.recipe_finder.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey
    private int id;
    private String name;
    private String category;
    private String area;

    private String cookingInstructions;
    private String image;
    private String recipeLink;

    private ArrayList<String> ingredients;
    private ArrayList<String> measures;

    public Recipe(int id, String name, String category, String area, String cookingInstructions, String image, String recipeLink, ArrayList<String> ingredients, ArrayList<String> measures)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.cookingInstructions = cookingInstructions;
        this.image = image;
        this.recipeLink = recipeLink;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public String getImage() {
        return image;
    }

    public String getRecipeLink() {
        return recipeLink;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getMeasures() {
        return measures;
    }
}
