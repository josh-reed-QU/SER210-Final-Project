package edu.quinnipiac.ser210.remindersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    private EditText eventNameEdt, eventDateEdt, eventTimeEdt, eventDescriptionEdt;
    private Button addEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_event);

        eventNameEdt = findViewById(R.id.eventNameInput);
        eventDateEdt = findViewById(R.id.eventDateInput);
        eventTimeEdt = findViewById(R.id.eventTimeInput);
        eventDescriptionEdt = findViewById(R.id.eventDescriptionInput);
        addEventBtn = findViewById(R.id.addEventButton);

        dbHandler = new DataBaseHandler(EventActivity.this);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventCategory = "ALL";
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
                eventNameEdt.setText("");
                eventDateEdt.setText("");
                eventTimeEdt.setText("");
                eventDescriptionEdt.setText("");
            }
        });

    }
}