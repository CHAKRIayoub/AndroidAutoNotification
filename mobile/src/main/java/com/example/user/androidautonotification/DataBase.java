package com.example.user.androidautonotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 19/06/2017.
 */

public class DataBase extends SQLiteOpenHelper {


    static final String DB_NAME = "DataBaseNotifications.db";
    static final int version = 1;


    public DataBase(Context context) {

        super(context, DB_NAME, null, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Notification(id integer primary key);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Notification");
        onCreate(db);

    }

    public int get_last_id(){

        int id = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("select MAX(id) from Notification", null);

        while (cur.moveToNext()) {
            id =  cur.getInt(0);
        }

        return id;
    }

    public void inset_id(int id){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("id", id);
        db.insert("Notification",null,val);

    }
}
