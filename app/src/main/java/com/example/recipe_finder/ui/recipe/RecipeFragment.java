package com.example.recipe_finder.ui.recipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.Recipe;
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
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeTitle = view.findViewById(R.id.recipe_title);

        recipeImage = view.findViewById(R.id.recipe_image_full);

        ingredientsMeasures = view.findViewById(R.id.recipe_ingredients_measures);

        recipeCookingInstructions = view.findViewById(R.id.recipe_cooking_instructions);

        shareButton = view.findViewById(R.id.share_button);
        shareButton.setOnClickListener(this::shareButtonPressed);

        favouriteButton = view.findViewById(R.id.favourite_button);
        favouriteButton.setOnClickListener(this::favouriteButtonPressed);

        videoButton = view.findViewById(R.id.video_button);
        videoButton.setOnClickListener(this::videoButtonPressed);

        recipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        recipeViewModel.observeRecipe().observe(getViewLifecycleOwner(), this::addRecipe);

        if (getArguments() != null && getArguments().get("RecipeId") != null) {
            recipeViewModel.updateRecipeById(getArguments().get("RecipeId") + "");
        } else recipeViewModel.updateRecipeRandom();
    }

    private void shareButtonPressed(View view) {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/html");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this awesome recipe!");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                recipeViewModel.getRecipe().getName()
                        + "\n Recipe link: " + recipeViewModel.getRecipe().getRecipeLink());
        startActivity(Intent.createChooser(shareIntent, "Share Recipe"));

    }

    private void favouriteButtonPressed(View view) {

        String textToShow = recipeViewModel.getFavouriteStatus() ? "Removed from favourites" : "Added to favourites";

        recipeViewModel.toggleFavouriteButton();

        Toast.makeText(getContext(), textToShow, Toast.LENGTH_SHORT).show();
    }

    private void videoButtonPressed(View view) {
        String action = Intent.ACTION_VIEW;
        Uri uri = Uri.parse(recipeViewModel.getRecipe().getRecipeLink());
        Intent intent = new Intent(action, uri);
        startActivity(intent);
    }


    private void addRecipe(Recipe recipe) {

        if(recipe==null) return;

        recipeTitle.setText(recipe.getName());

        Picasso.get()
                .load(recipe.getImage())
                .fit()
                .centerCrop()
                .into(recipeImage);

        ArrayList<String> ingredients = recipe.getIngredients();
        ArrayList<String> measures = recipe.getMeasures();

        ingredientsMeasures.removeAllViews();

        for (int i = 0; i < ingredients.size(); i++) {
            TableRow tableRow = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(lp);

            TextView ingredient = new TextView(getContext());
            ingredient.setText(ingredients.get(i));
            tableRow.addView(ingredient);
            TextView measure = new TextView(getContext());

            if (i < measures.size()) {
                measure.setText(measures.get(i));
                tableRow.addView(measure);
            }

            ingredientsMeasures.addView(tableRow, i);
        }

        recipeCookingInstructions.setText(recipe.getCookingInstructions());

    }


}
