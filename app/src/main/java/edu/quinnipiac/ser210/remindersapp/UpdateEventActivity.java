package edu.quinnipiac.ser210.remindersapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateEventActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private Cursor cursor;

    private EditText eventNameEdt, eventDateEdt, eventTimeEdt, eventDescriptionEdt;
    private Button updateEventBtn, deleteEventBtn;

    String eventName, eventDesc, eventDate, eventTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_update_event);

        eventNameEdt = findViewById(R.id.categoryNameInput);
        eventDateEdt = findViewById(R.id.eventDateInput);
        eventTimeEdt = findViewById(R.id.eventTimeInput);
        eventDescriptionEdt = findViewById(R.id.eventDescriptionInput);

        updateEventBtn = findViewById(R.id.updateEventButton);
        deleteEventBtn = findViewById(R.id.deleteEventButton);

        dbHandler = new DataBaseHandler(UpdateEventActivity.this);

        eventName = getIntent().getStringExtra("name");
        eventDesc = getIntent().getStringExtra("description");
        eventDate = getIntent().getStringExtra("date");
        eventTime = getIntent().getStringExtra("time");

        eventNameEdt.setText(eventName);
        eventDateEdt.setText(eventDesc);
        eventTimeEdt.setText(eventDate);
        eventDescriptionEdt.setText(eventTime);

        updateEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateEvent(eventName, eventNameEdt.getText().toString(), eventDescriptionEdt.getText().toString(), eventDateEdt.getText().toString(), eventTimeEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateEventActivity.this, "Event Updated", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateEventActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our course.
        deleteEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteEvent(eventName);
                Toast.makeText(UpdateEventActivity.this, "Event Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateEventActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}
