package com.example.memorism.memory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.memorism.memory.MemoryContent.ITEM_MAP;
import static com.example.memorism.memory.MemoryContent.ITEMS;

/**
 * Created by farid on 27/12/17.
 */


public class DBHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "Memorism_database.db";
    public static final String TABLE_NAME = "memory_content";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TRIP_NAME = "trip_name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        /*db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );*/


        try {
            db.execSQL(
                    "create table " + TABLE_NAME + "(" +
                            COLUMN_ID + " integer primary key" + ","
                            + COLUMN_TRIP_NAME + " string,"
                            + COLUMN_DATE + " string,"
                            + COLUMN_TITLE + " string,"
                            + COLUMN_DETAILS + " string,"
                            + COLUMN_LATITUDE + " float,"
                            + COLUMN_LONGITUDE + " float)"

            );
        }
        catch (Exception e)
        {

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void reset_dataBase(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMemoryIntoDB(MemoryContent.MemoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRIP_NAME, item.getTrip_name());
        contentValues.put(COLUMN_DATE, item.getDate());
        contentValues.put(COLUMN_TITLE, item.getTitle());
        contentValues.put(COLUMN_DETAILS,item.getDetails());
        contentValues.put(COLUMN_LATITUDE,item.getLatitude());
        contentValues.put(COLUMN_LONGITUDE,item.getLongitude());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public MemoryContent.MemoryItem getItemFromDB(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_DATE +"="+date, null );
        MemoryContent.MemoryItem this_item = new MemoryContent.MemoryItem();
        this_item.setTrip_name(res.getString(res.getColumnIndex(COLUMN_TRIP_NAME)));
        this_item.setDate(res.getString(res.getColumnIndex(COLUMN_DATE)));
        this_item.setTitle(res.getString(res.getColumnIndex(COLUMN_TITLE)));
        this_item.setDetails(res.getString(res.getColumnIndex(COLUMN_DETAILS)));
        this_item.setPosition(res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)));

        return this_item;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateMemory (String trip_name, String date, String newTitle, String newDetails, float newLatitude, float newLongitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRIP_NAME, trip_name);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TITLE, newTitle);
        contentValues.put(COLUMN_DETAILS,newDetails);
        contentValues.put(COLUMN_LATITUDE,newLatitude);
        contentValues.put(COLUMN_LONGITUDE,newLongitude);
        db.update(TABLE_NAME, contentValues, COLUMN_DATE+" = ? ",new String[] {date} );
        return true;
    }

    public Integer deleteMemory (String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_DATE+" = ? ",
                new String[] { date });
    }

    public void getAllMemory() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        MemoryContent.MemoryItem this_item = new MemoryContent.MemoryItem();

        while(res.isAfterLast() == false){
            this_item = new MemoryContent.MemoryItem();
            this_item.setTrip_name(res.getString(res.getColumnIndex(COLUMN_TRIP_NAME)));
            this_item.setDate(res.getString(res.getColumnIndex(COLUMN_DATE)));
            this_item.setTitle(res.getString(res.getColumnIndex(COLUMN_TITLE)));
            this_item.setDetails(res.getString(res.getColumnIndex(COLUMN_DETAILS)));
            this_item.setPosition(res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)));
            Log.d("\nitem","\nitem date"+ this_item.getDate());
            ITEMS.add(this_item);
            ITEM_MAP.put(this_item.date, this_item);

            res.moveToNext();
        }
    }
}