package com.example.recipe_finder.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.Recipe;
import com.example.recipe_finder.ui.recipe_list.RecipeListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    TextView recipeTitle;

    ImageView recipeImage;

    TableLayout ingredientsMeasures;

    TextView recipeCookingInstructions;

    ImageButton shareButton;
    ImageButton favouriteButton;
    ImageButton videoButton;

    RecipeViewModel recipeViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeTitle = view.findViewById(R.id.recipe_title);

        recipeImage = view.findViewById(R.id.recipe_image_full);

        ingredientsMeasures = view.findViewById(R.id.recipe_ingredients_measures);

        recipeCookingInstructions = view.findViewById(R.id.recipe_cooking_instructions);

        shareButton = view.findViewById(R.id.share_button);

        favouriteButton = view.findViewById(R.id.favourite_button);

        videoButton = view.findViewById(R.id.video_button);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipe().observe(getViewLifecycleOwner(), this::addRecipe);

    }

    private void addRecipe(Recipe recipe) {

        recipeTitle.setText(recipe.getName());

        Picasso.get()
                .load(recipe.getImage())
                .fit()
                .centerCrop()
                .into(recipeImage);


        ArrayList<String> ingredients = recipe.getIngredients();
        ArrayList<String> measures = recipe.getMeasures();

        for(int i = 0; i<recipe.getIngredients().size(); i++){
            TableRow tableRow = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(lp);

            TextView ingredient = new TextView(getContext());
            ingredient.setText(ingredients.get(i));
            TextView measure = new TextView(getContext());
            measure.setText(measures.get(i));

            tableRow.addView(ingredient);
            tableRow.addView(measure);

            ingredientsMeasures.addView(tableRow,i);

        }


        recipeCookingInstructions.setText(recipe.getCookingInstructions());

    }


}
