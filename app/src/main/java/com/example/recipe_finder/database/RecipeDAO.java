package com.example.recipe_finder.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recipe_finder.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Recipe recipe);

    @Update
    void update (Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("SELECT * FROM Recipe ORDER BY name")
    List<Recipe> getAllFavourites();

    @Query("SELECT * FROM Recipe WHERE id == :recipeId")
    LiveData<Recipe> getRecipeById(int recipeId);

    @Query("SELECT EXISTS (SELECT 1 FROM Recipe WHERE id == :recipeId)")
    boolean exists (int recipeId);

}
