package com.example.memorism.memory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by farid on 27/12/17.
 */


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Memorism_database.db";
    public static final String TABLE_NAME = "memory_content";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    private HashMap hp;

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
        db.execSQL(
                "create table " + TABLE_NAME + "("+
                        COLUMN_ID + " integer primary key" + ","
                        + COLUMN_DATE + " string,"
                        + COLUMN_TITLE + " string,"
                        + COLUMN_DETAILS + " string,"
                        + COLUMN_LATITUDE + " float,"
                        + COLUMN_LONGITUDE + " float)"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (MemoryContent.MemoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATE, item.getDate());
        contentValues.put(COLUMN_TITLE, item.getTitle());
        contentValues.put(COLUMN_DETAILS,item.getDetails());
        contentValues.put(COLUMN_LATITUDE,item.getLatitude());
        contentValues.put(COLUMN_LONGITUDE,item.getLongitude());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_DATE +"="+date, null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateMemory (String date, String newTitle, String newDetails, float newLatitude, float newLongitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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

  /*  public ArrayList<String> getAllMemory() {
  /*      ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
         //   array_list.add(res.getString(res.getColumnIndex(MEMORISM_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }*/
}