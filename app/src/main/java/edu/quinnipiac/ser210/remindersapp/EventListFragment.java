package edu.quinnipiac.ser210.remindersapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment implements View.OnClickListener {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    // NavController object to allow the fragment to switch screens
    NavController navController = null;

    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        //TODO: add a button to splash screen, implement onClickListener Here, will always have the all category

        ListView listEvents = (ListView) view.findViewById(R.id.list_events);

        dbHandler = new DataBaseHandler(view.getContext());

        System.out.println(dbHandler.readEvents());
        System.out.println(dbHandler.getTableAsString("reminders"));

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");

        ArrayAdapter<EventModal> arrayAdapter = new ArrayAdapter<EventModal>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                dbHandler.readEvents() );

        listEvents.setAdapter(arrayAdapter);

        view.findViewById(R.id.eventListFAB).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //TODO: navigate to next screen using navController
        switch(view.getId()) {
            case R.id.eventListFAB:
                navController.navigate(R.id.action_eventListFragment_to_addEventFragment);
                break;
        }
    }
}