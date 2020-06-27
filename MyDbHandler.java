package com.example.myapplication382.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication382.model.Detections;
import com.example.myapplication382.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {


    public MyDbHandler( Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_ADDRESS + " TEXT, " + Params.KEY_TIME + " TEXT)";
        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void addDetections(Detections detections)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME,detections.getName());
        values.put(Params.KEY_ADDRESS,detections.getAddress());
        values.put(Params.KEY_TIME,detections.getTime());

        db.insert(Params.TABLE_NAME,null,values);
        db.close();
    }

    public List<Detections> getAllDetections(){
        List<Detections> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Detections detections = new Detections();
                detections.setId(Integer.parseInt(cursor.getString(0)));
                detections.setName(cursor.getString(1));
                detections.setAddress(cursor.getString(2));
                detections.setTime(cursor.getString(3));
                contactList.add(detections);
            }
            while(cursor.moveToNext());
        }
        return contactList;
    }

}
