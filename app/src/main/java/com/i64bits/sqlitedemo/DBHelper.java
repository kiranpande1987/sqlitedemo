package com.i64bits.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String TAG = "DBHelper";

    public static final String DB_NAME = "user.db";
    public static final String TABLE_NAME = "User";

    public static final String COL_0 = "ID";
    public static final String COL_1 = "name";
    public static final String COL_2 = "age";
    public static final String COL_3 = "location";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE INTEGER, LOCATION TEXT)");
        Log.e(TAG, "onCreate() returned: " + "CALLED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(User user)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = Utils.modelToContentValues(user);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return (result != -1);
    }
}
