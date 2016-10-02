package com.example.user.assignment_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 02-Oct-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private String dbName;
    private Context context;
    public DatabaseHelper(Context context,String dbName){
        super(context, dbName, null, 1);
        this.dbName=dbName;
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists info (value varchar);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
