package edu.quinnipiac.ser210.remindersapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class HelpScreenFragment extends Fragment {
    // NavController object to allow for navigation between screens
    NavController navController = null;

    public HelpScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        // sets toolbar for fragment
        AppCompatActivity context = (AppCompatActivity)getContext();
        context.setSupportActionBar(getView().findViewById(R.id.toolbarHelpScreen));
        setHasOptionsMenu(true);
        //TODO: implement back arrow on action bar
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.help:
                break;
            case R.id.settings:
                navController.navigate(R.id.action_helpScreenFragment_to_settingsFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}