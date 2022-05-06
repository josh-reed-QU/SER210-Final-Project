package edu.quinnipiac.ser210.remindersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    private EditText eventNameEdt, eventDateEdt, eventTimeEdt, eventDescriptionEdt, eventCategoryEdt;
    private Button addEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_event);

        eventCategoryEdt = findViewById(R.id.categoryNameInput2);
        eventNameEdt = findViewById(R.id.eventNameInput);
        eventDateEdt = findViewById(R.id.eventDateInput);
        eventTimeEdt = findViewById(R.id.eventTimeInput);
        eventDescriptionEdt = findViewById(R.id.eventDescriptionInput);
        addEventBtn = findViewById(R.id.addCategoryButton);

        dbHandler = new DataBaseHandler(EventActivity.this);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventCategory = eventCategoryEdt.getText().toString();
                String eventName = eventNameEdt.getText().toString();
                String eventDate = eventDateEdt.getText().toString();
                String eventTime = eventTimeEdt.getText().toString();
                String eventDescription = eventDescriptionEdt.getText().toString();

                if (eventName.isEmpty() && eventDate.isEmpty() && eventTime.isEmpty()) {
                    Toast.makeText(EventActivity.this, "Please enter name, date and time..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewEvent(eventCategory, eventName, eventDate, eventTime,  eventDescription);

                Toast.makeText(EventActivity.this, "Event has been added.", Toast.LENGTH_SHORT).show();
                eventCategoryEdt.setText("");
                eventNameEdt.setText("");
                eventDateEdt.setText("");
                eventTimeEdt.setText("");
                eventDescriptionEdt.setText("");

                NavController navController = Navigation.findNavController(v);

                navController.navigate(R.id.action_homeScreenFragment_to_eventListFragment);
            }
        });

    }
}