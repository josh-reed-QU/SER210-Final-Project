package edu.quinnipiac.ser210.remindersapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class AddCategoryFragment extends Fragment implements View.OnClickListener {
    private DataBaseHandler dbHandler;

    // NavController object to allow navigation between fragments
    NavController navController = null;

    private EditText categoryNameEdt;
    private Button addCategoryBtn;

    public AddCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        // sets toolbar for fragment
        AppCompatActivity context = (AppCompatActivity)getContext();
        context.setSupportActionBar(getView().findViewById(R.id.toolbarAddCategoryScreen));
        setHasOptionsMenu(true);

        categoryNameEdt = view.findViewById(R.id.eventNameInput);
        addCategoryBtn = view.findViewById(R.id.addCategoryButton);

        dbHandler = new DataBaseHandler(view.getContext());

        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = categoryNameEdt.getText().toString();

                if (categoryName.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter category name..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewCategory(categoryName);

                Toast.makeText(view.getContext(), "Category has been added.", Toast.LENGTH_SHORT).show();
                categoryNameEdt.setText("");
            }
        });

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
                navController.navigate(R.id.action_addCategoryFragment_to_helpScreenFragment);
                break;
            case R.id.settings:
                navController.navigate(R.id.action_addEventFragment_to_settingsFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        //TODO: navigate to next screen using navController
    }
}
