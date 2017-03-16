package com.alex.vestlist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class CrudImpl<T> implements CrudInterface<T> {
    private Context context;
    private String tableName;
    private BaseModel<T> factory;

    public CrudImpl(Context context, String tableName, BaseModel<T> baseObject) {
        this.context = context;
        this.tableName = tableName;
        this.factory = baseObject;
    }

    @Override
    public T get(long id) {
        List<T> list = search(BaseColumns._ID, String.valueOf(id), 1);
        if (list == null && list.size() > 1 || list.isEmpty() )
            return null;
        T t = list.get(0);
        return t;
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
    public List<T> search(String key, String value, int limit) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String selection = key + "=?";
        String[] selectionArgs = {value};
        Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null, String.valueOf(limit));

        return factory.getList(cursor);
    }

    @Override
    public List<T> load(int offset, int limit) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String limitString = limit + " OFFSET " + offset;
        Cursor cursor = readableDatabase.query(tableName, null, null, null, null, null, null, limitString);

        return factory.getList(cursor);
    }

    @Override
    public int update(long id, ContentValues values) {
        return 0;
    }
}
