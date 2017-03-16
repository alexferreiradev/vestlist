package com.alex.vestlist.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class CrudImpl<T> implements CrudInterface<T> {
    private Context context;
    private String tableName;

    @Override
    public T get(long id) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String selection = BaseColumns._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null, "1");

        return null;
    }

    @Override
    public long save(T object) {
        return -1;
    }

    @Override
    public void delete(T object) {

    }

    @Override
    public List<T> search(String key, String value) {
        return null;
    }
}
