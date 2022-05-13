package com.example.recipe_finder.model;

public class Cuisine {
    private String strArea;

    public Cuisine(String categoryName) {
        this.strArea = categoryName;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
