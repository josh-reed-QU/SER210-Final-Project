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

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*cursor adapter for getting events info to populate listview
        SQLiteOpenHelper reminderDatabaseHelper = new DataBaseHandler(this);
        try{
            db = reminderDatabaseHelper.getReadableDatabase();
            cursor = db.query("reminders", new String[]{"_id", "category"},
                    null,null,null,null,null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                    cursor,new String[]{"category"},new int[]{android.R.id.text1},0);

            listEvents.setAdapter(listAdapter);
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }*/


    }
}