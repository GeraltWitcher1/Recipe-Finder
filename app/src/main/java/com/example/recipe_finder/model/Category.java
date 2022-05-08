package com.example.recipe_finder.model;

public class Category {
    private String strCategory;

    public Category(String categoryName) {
        this.strCategory = categoryName;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
}
