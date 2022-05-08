package edu.quinnipiac.ser210.remindersapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddEventFragment extends Fragment implements View.OnClickListener {
    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    private EditText eventNameEdt, eventDateEdt, eventTimeEdt, eventDescriptionEdt, eventCategoryEdt;
    private Button addEventBtn, eventTimePicker;

    private TimePicker timerPicker;
    private DatePicker datePicker;

    // NavController object to allow navigation between fragments
    NavController navController = null;

    public AddEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        // sets toolbar for fragment
        AppCompatActivity context = (AppCompatActivity)getContext();
        context.setSupportActionBar(getView().findViewById(R.id.toolbarAddEventScreen));
        setHasOptionsMenu(true);

        eventCategoryEdt = view.findViewById(R.id.categoryNameInput2);
        eventNameEdt = view.findViewById(R.id.eventNameInput);
        eventDateEdt = view.findViewById(R.id.eventDateInput);
        eventTimeEdt = view.findViewById(R.id.eventTimeInput);
        eventDescriptionEdt = view.findViewById(R.id.eventDescriptionInput);
        addEventBtn = view.findViewById(R.id.addCategoryButton);

        timerPicker = (TimePicker) view.findViewById(R.id.timerPicker1);
        timerPicker.setIs24HourView(false);

        datePicker = (DatePicker) view.findViewById(R.id.datePicker1);

        dbHandler = new DataBaseHandler(view.getContext());

        eventCategoryEdt.setText(getArguments().getString("category"));

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventCategory = eventCategoryEdt.getText().toString();
                String eventName = eventNameEdt.getText().toString();
                String eventDescription = eventDescriptionEdt.getText().toString();

                int hour, minute;
                String am_pm;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = timerPicker.getHour();
                    minute = timerPicker.getMinute();
                }
                else{
                    hour = timerPicker.getCurrentHour();
                    minute = timerPicker.getCurrentMinute();
                }
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }


                String eventTime = (hour +":"+ minute+" "+am_pm).toString();

                String eventDate = (datePicker.getDayOfMonth()+"/"+ (datePicker.getMonth() + 1)+"/"+datePicker.getYear()).toString();

                if (eventName.isEmpty() && eventDate.isEmpty() && eventTime.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter name, date and time..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewEvent(eventCategory, eventName, eventDate, eventTime,  eventDescription);

                Toast.makeText(view.getContext(), "Event has been added.", Toast.LENGTH_SHORT).show();
                eventNameEdt.setText("");
                eventDescriptionEdt.setText("");
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
                navController.navigate(R.id.action_addEventFragment_to_helpScreenFragment);
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