package com.i64bits.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Utils
{
    private static final String TAG = "Utils";
    
    public static ContentValues modelToContentValues(Object model)
    {
        ContentValues contentValues = new ContentValues();

        try
        {
            for (Field field: model.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(model);

                if(value.toString().equalsIgnoreCase("-1")) continue;

                Log.e(TAG, "modelToContentView: "+ name + "-" + value.toString());

                contentValues.put(name, value.toString());
            }
        }
        catch (IllegalAccessException e)
        {
            Log.e(TAG, "modelToContentValues: Exception : ContentValue Cleared : " + e.getLocalizedMessage());
            contentValues.clear();
        }

        return contentValues;
    }

    public static List<User> cursorToModel(Cursor cursor)
    {
        List<User> users = new ArrayList<>();

        if(cursor != null && cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                User user = new User(cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                users.add(user);
            }
        }

        return users;
    }

    public static boolean isNullOrEmpty(String string)
    {
        return (string == null || string.isEmpty());
    }
}
