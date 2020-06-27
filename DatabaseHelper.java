package com.example.myapplication382;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="detection.db";
    public static final String TABLE_NAME="detection_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="MAC";
    public static final String COL_4="TIME";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MAC TEXT, TIME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    public boolean insertData(String name, String mac, String time)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, mac);
        contentValues.put(COL_4, time);

        long result=db.insert(TABLE_NAME, null, contentValues);

        if (result==-1)
        {
            return false;
        }

        else
        {
            return true;
        }

    }

    public List<String> getAll(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{ list.add("Device: " +cursor.getString(1) + "\n" + "\n" + "MAC Address: "+ cursor.getString(2) + "\n" + "\n" + cursor.getString(3));



            }while(cursor.moveToNext());
        }
        return list;
    }



}
