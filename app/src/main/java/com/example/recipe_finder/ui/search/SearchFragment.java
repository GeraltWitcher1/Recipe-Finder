package com.example.recipe_finder.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recipe_finder.R;
import com.example.recipe_finder.ui.recipe_list.RecipeListViewModel;

public class SearchFragment extends Fragment {

    EditText searchField;
    ImageView searchButton;

    RecipeListViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchField = view.findViewById(R.id.recipe_search_text);
        searchButton = view.findViewById(R.id.recipe_search_button);

        viewModel = new ViewModelProvider(requireActivity()).get(RecipeListViewModel.class);

        searchButton.setOnClickListener(this::searchButtonPressed);

    }

    private void searchButtonPressed(View view) {
        viewModel.setSearchString(searchField.getText().toString());
        NavHostFragment.findNavController(this).navigate(R.id.action_nav_search_to_nav_recipe_list);
    }

}
