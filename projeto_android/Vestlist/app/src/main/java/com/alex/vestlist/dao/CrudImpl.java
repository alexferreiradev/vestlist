package com.alex.vestlist.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.alex.vestlist.model.ModelSqlInterface;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class CrudImpl<T> implements CrudInterface<T> {
    private Context context;
    private String tableName;
    private ModelSqlInterface<T> factory;

    public CrudImpl(Context context, String tableName, ModelSqlInterface baseObject) {
        this.context = context;
        this.tableName = tableName;
        this.factory = baseObject;
    }

    @Override
    public T get(long id) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String selection = BaseColumns._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null, "1");

        return factory.convertCursor(cursor);
    }

    @Override
    public long save(T object) {
        SQLiteDatabase writableDatabase = new DatabaseHelper(context).getWritableDatabase();
        writableDatabase.beginTransaction();
        long id = writableDatabase.insert(tableName, null, factory.toContentValues());

        return id;
    }

    @Override
    public int delete(long id) {
        SQLiteDatabase writableDatabase = new DatabaseHelper(context).getWritableDatabase();
        String where = BaseColumns._ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        int rowsDeleted = writableDatabase.delete(tableName, where, whereArgs);
        return rowsDeleted;
    }

    @Override
    public List<T> search(String key, String value, String limit) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String selection = key + "=?";
        String[] selectionArgs = {value};
        Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null, limit);

        return factory.getList(cursor);
    }
}
