package edu.quinnipiac.ser210.remindersapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeScreenFragment extends Fragment implements View.OnClickListener{
    NavController navController = null;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        //TODO: get buttons from screen, implement onClickListener here
        view.findViewById(R.id.allEventsButton).setOnClickListener(this);
        view.findViewById(R.id.homeScreenFAB).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //TODO: implement for the category buttons
        switch(view.getId()) {
            case R.id.allEventsButton:
                //TODO: only goes to screen now, does not change events displayed
                navController.navigate(R.id.action_homeScreenFragment_to_eventListFragment);
                break;
            case R.id.homeScreenFAB:
                navController.navigate(R.id.action_homeScreenFragment_to_addEventFragment);
                break;
        }
    }
}