package edu.quinnipiac.ser210.remindersapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EventListFragment extends Fragment implements View.OnClickListener {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    private ArrayList<EventModal> eventArrayList;
    private EventCVAdapter eventCVAdapter;
    private RecyclerView eventsCV;

    private String currentCategory;

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
        // sets toolbar for fragment
        AppCompatActivity context = (AppCompatActivity)getContext();
        context.setSupportActionBar(getView().findViewById(R.id.toolbarEventListScreen));
        setHasOptionsMenu(true);

        eventArrayList = new ArrayList<>();
        dbHandler = new DataBaseHandler(view.getContext());

        System.out.println("bundle Category");

        currentCategory = getArguments().getString("category");

        eventArrayList = dbHandler.readEvents(currentCategory);

        eventCVAdapter = new EventCVAdapter(eventArrayList, view.getContext());
        eventsCV = view.findViewById(R.id.idCVCategories);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        eventsCV.setLayoutManager(linearLayoutManager);

        eventsCV.setAdapter(eventCVAdapter);

        System.out.println("read categories");
        System.out.println(dbHandler.readCategories());

        System.out.println("read events");
        System.out.println(dbHandler.readEvents(""));
        System.out.println(dbHandler.getTableAsString("reminders"));

        view.findViewById(R.id.eventListFAB).setOnClickListener(this);
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
                navController.navigate(R.id.action_eventListFragment_to_helpScreenFragment);
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
        switch(view.getId()) {
            case R.id.eventListFAB:

                Bundle bundle = new Bundle();
                bundle.putString("category", currentCategory);

                navController.navigate(R.id.action_eventListFragment_to_addEventFragment, bundle);
                break;
        }
    }
}