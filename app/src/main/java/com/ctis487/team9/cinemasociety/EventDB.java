package com.ctis487.team9.cinemasociety;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class EventDB {
    public static String TABLE_NAME = "event";
    public static String FIELD_ID= "id";
    public static String FIELD_TITLE = "title";
    public static String FIELD_YEAR = "year";
    public static String FIELD_DIRECTOR = "director";
    public static String FIELD_DATE = "date";
    public static String FIELD_TIME = "time";
    public static String FIELD_IMAGE = "image";

    public static String CREATE_TABLE_SQL = "CREATE TABLE TABLE_NAME ( "+ FIELD_ID+ " INTEGER, "+FIELD_TITLE+" TEXT, "+FIELD_YEAR+" TEXT, "+ FIELD_DIRECTOR+" TEXT, "
                                            + FIELD_DATE + " TEXT, " + FIELD_TIME + " TEXT, " + FIELD_IMAGE+ " TEXT, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Event> getAllEvents(DatabaseHelper dbHelper){
        Event anItem;
        ArrayList<Event> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String title = cursor.getString(1);
            String year = cursor.getString(2);
            String director= cursor.getString(3);
            String date= cursor.getString(4);
            String time= cursor.getString(5);
            String image= cursor.getString(6);

            anItem = new Event(id, title, year, director, date, time, image);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }
}
