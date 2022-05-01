package com.example.recipe_finder.ui.recipe_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_finder.R;
import com.example.recipe_finder.model.RecipeListItem;
import com.example.recipe_finder.utility.RecipeListAdapter;

import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    RecyclerView recipeList;
    RecipeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        recipeList = root.findViewById(R.id.recipeRecyclerView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<RecipeListItem> recipes = new ArrayList<>();
        adapter = new RecipeListAdapter(recipes);
        recipeList.setAdapter(adapter);

//        populateArray(recipes);

        return root;
    }

    private void populateArray(ArrayList<RecipeListItem> testArray) {
        testArray.add(new RecipeListItem(1, "Carbonara", "https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/llcbn01574260722.jpg"));
        testArray.add(new RecipeListItem(2, "Arrabiata", "https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/ustsqw1468250014.jpg"));
        testArray.add(new RecipeListItem(3, "Alfredo", "https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/ustsqw1468250014.jpg"));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));
        testArray.add(new RecipeListItem(4, "Amatriciana", null));

    }
}
