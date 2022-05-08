package edu.quinnipiac.ser210.remindersapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";
    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "reminders";
    private static final String ID_COL = "id";
    private static final String CATEGORY_COL = "category";
    private static final String NAME_COL = "name";
    private static final String DESCRIPTION_COL = "description";
    private static final String DATE_COL = "date";
    private static final String TIME_COL = "time";
    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    private String getCurrentTime() { // Returns Current Time from user as string
        String delegate = "hh:mm aaa";
        return (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
    }
    private String getCurrentDate() { // Returns current date from user as string
        String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        return currentDate;
    }
    @SuppressLint("Range")
    public String getTableAsString(String tableName) { // Returns data table into string
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";
            } while (allRows.moveToNext());
        }
        return tableString;
    }

    public void addNewCategory(String eventCategory) {
        // TODO: Link this to add event button, make sure the category is correct and set by the system, not user
        // Parameters: Category, Name, Date, Time, Description
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_COL, eventCategory);
        values.put(NAME_COL, "");
        values.put(DATE_COL, "");
        values.put(TIME_COL, "");
        values.put(DESCRIPTION_COL, "");
        /*String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        values.put(DATE_COL, currentDate);
        values.put(TIME_COL, getRemainingTime());*/
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void addNewEvent(String eventCategory, String eventName, String eventDate, String eventTime, String eventDescription) {
        // TODO: Link this to add event button, make sure the category is correct and set by the system, not user
        // Parameters: Category, Name, Date, Time, Description
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_COL, eventCategory);
        values.put(NAME_COL, eventName);
        values.put(DATE_COL, eventDate);
        values.put(TIME_COL, eventTime);
        values.put(DESCRIPTION_COL, eventDescription);
        /*String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        values.put(DATE_COL, currentDate);
        values.put(TIME_COL, getRemainingTime());*/
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateEvent(String originalEventName, String eventName, String eventDescription,
                            String eventDate, String eventTime) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, eventName);
        values.put(DATE_COL, eventDate);
        values.put(DESCRIPTION_COL, eventDescription);
        values.put(TIME_COL, eventTime);

        // update method to update our database and passing our values.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalEventName});
        db.close();
    }

    public void deleteEvent(String eventName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "name=?", new String[]{eventName});
        db.close();
    }

    public ArrayList<CategoryModal> readCategories() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCategories = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + " =? ", new String[] {""});

        // on below line we are creating a new array list.
        ArrayList<CategoryModal> categoryModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCategories.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                categoryModalArrayList.add(new CategoryModal(cursorCategories.getString(1)));
            } while (cursorCategories.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCategories.close();
        return categoryModalArrayList;
    }

    public ArrayList<EventModal> readEvents(String Category) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorEvents;

        System.out.println("Category : " + Category);
        if (Category.equals("\"\"") == true || Category.equals("ALL") == true) {
            cursorEvents = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        } else {
            cursorEvents = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CATEGORY_COL + " =? ", new String[] {Category});
        }

        // on below line we are creating a new array list.
        ArrayList<EventModal> eventModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorEvents.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                if (cursorEvents.getString(2).isEmpty() == false) {
                    eventModalArrayList.add(new EventModal(cursorEvents.getString(1),
                            cursorEvents.getString(2),
                            cursorEvents.getString(3),
                            cursorEvents.getString(4),
                            cursorEvents.getString(5)));
                }

            } while (cursorEvents.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorEvents.close();
        return eventModalArrayList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
