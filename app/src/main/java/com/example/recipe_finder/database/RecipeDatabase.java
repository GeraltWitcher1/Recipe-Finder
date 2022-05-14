package com.example.recipe_finder.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recipe_finder.model.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase instance;

    public abstract RecipeDAO recipeDAO();

    public static synchronized RecipeDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                            RecipeDatabase.class, "recipe_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}