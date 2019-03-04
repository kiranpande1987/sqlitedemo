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

        for (Field field: model.getClass().getDeclaredFields()) 
        {
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(model);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "modelToContentView: "+ name + "-" + value.toString());
            
        }

        return contentValues;
    }
}
