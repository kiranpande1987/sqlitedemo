package com.i64bits.sqlitedemo;

import android.content.ContentValues;
import android.util.Log;

import java.lang.reflect.Field;

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
}
