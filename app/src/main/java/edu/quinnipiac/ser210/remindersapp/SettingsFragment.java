package edu.quinnipiac.ser210.remindersapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Objects;


public class SettingsFragment extends Fragment {
    NavController navController = null;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        // sets toolbar for fragment
        AppCompatActivity context = (AppCompatActivity)getContext();
        context.setSupportActionBar(getView().findViewById(R.id.toolbarSettingsScreen));
        setHasOptionsMenu(true);
        Spinner spinner = getView().findViewById(R.id.themeSpinner);
        //sets spinner onItemSelectedListener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i) {
                    case 0:
                        getActivity().setTheme(R.style.BlueTheme);
                        break;
                    case 1:
                        getActivity().setTheme(R.style.RedTheme);
                        break;
                    case 2:
                        getActivity().setTheme(R.style.GreenTheme);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }


}