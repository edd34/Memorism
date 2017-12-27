package com.example.memorism.memory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by farid on 27/12/17.
 */

public class MyDataAccessObject {
    private SQLiteDatabase database;
    private MyDatabaseHandler dbHelper;
    private String[] allColumns =
            {MyDatabaseHandler.COLUMN_DATE,
                    MyDatabaseHandler.COLUMN_TITLE,
                    MyDatabaseHandler.COLUMN_DETAILS,
                    MyDatabaseHandler.COLUMN_LATITUDE,
                    MyDatabaseHandler.COLUMN_LONGITUDE};

    public MyDataAccessObject(Context context){
        dbHelper = new MyDatabaseHandler(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public MemoryContent.MemoryItem addMemory_DB(MemoryContent.MemoryItem item)
    {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHandler.COLUMN_DATE,item.getDate());
        values.put(MyDatabaseHandler.COLUMN_TITLE,item.getTitle());
        values.put(MyDatabaseHandler.COLUMN_DETAILS,item.getDetails());
        values.put(MyDatabaseHandler.COLUMN_LATITUDE,item.getLatitude());
        values.put(MyDatabaseHandler.COLUMN_LONGITUDE,item.getLongitude());

        database.insert(MyDatabaseHandler.TABLE_MEMORY_ITEM,null,values);
        return item;
    }

    public void deleteMemory_db(MemoryContent.MemoryItem item)
    {
        database.delete(MyDatabaseHandler.TABLE_MEMORY_ITEM, MyDatabaseHandler.COLUMN_DATE
                + " = " + item.date, null);
    }
}
