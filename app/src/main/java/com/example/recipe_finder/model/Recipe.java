package com.example.recipe_finder.model;

import java.util.ArrayList;

public class Recipe {
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
}
