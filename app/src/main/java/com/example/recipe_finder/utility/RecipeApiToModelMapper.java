package com.example.recipe_finder.utility;

import com.example.recipe_finder.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class RecipeApiToModelMapper {

    public static Recipe map(JsonObject recipeResponse) {

        JsonObject recipeJObj = recipeResponse.get("meals").getAsJsonArray().get(0).getAsJsonObject();

        int id = recipeJObj.get("idMeal").getAsInt();
        String name = recipeJObj.get("strMeal").getAsString();
        String category = recipeJObj.get("strCategory").getAsString();
        String area = recipeJObj.get("strArea").getAsString();
        String cookingInstructions = recipeJObj.get("strInstructions").getAsString();
        String image = recipeJObj.get("strMealThumb").getAsString();
        String recipeLink = recipeJObj.get("strYoutube").getAsString();
        ArrayList<String> ingredients = parseArrayList(recipeJObj, "strIngredient");
        ArrayList<String> measures = parseArrayList(recipeJObj, "strMeasure");

        return new Recipe(id, name, category, area, cookingInstructions, image, recipeLink, ingredients, measures);
    }

    private static ArrayList<String> parseArrayList(JsonObject recipeJObj, String keyName) {

        Set<String> fields = recipeJObj.keySet();
        ArrayList<String> parsedList = new ArrayList<>();
        for (String obj : fields) {
            if (obj.startsWith(keyName)) {

                JsonElement iterateObj = recipeJObj.get(obj);

                if (!iterateObj.isJsonNull()
                        && !iterateObj.toString().isEmpty()
                        && !iterateObj.toString().equals("\"\"")  //need this check because the JSON values are enclosed in additional quotes
                        && !iterateObj.toString().equals(" ")) //need this because API values are inconsistent
                {
                    String ingredient = recipeJObj.get(obj).toString();
                    parsedList.add(ingredient.substring(1, ingredient.length() - 1));
                }

            }
        }
        return parsedList;
    }
}
