package com.example.recipe_finder.model;

public class RecipeListItem {

    private int recipeID;
    private String name;
    private String thumbURL;

    public int getRecipeID() {
        return recipeID;
    }

    public RecipeListItem(int recipeID, String name, String thumbURL) {
        this.recipeID = recipeID;
        this.name = name;
        this.thumbURL = thumbURL;
    }

    public String getName() {
        return name;
    }

    public String getThumbURL() {
        return thumbURL;
    }
}
