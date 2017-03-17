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

public class CrudImpl<T> implements CrudInterface<BaseModel> {
    private Context context;
    private String tableName;
    private BaseModel<? extends BaseModel> factory;

    public CrudImpl(Context context, String tableName, BaseModel<? extends BaseModel> baseObject) {
        this.context = context;
        this.tableName = tableName;
        this.factory = baseObject;
    }

    @Override
    public BaseModel get(long id) {
        List<BaseModel> list = search(BaseColumns._ID, String.valueOf(id), 0, 1);
        if (list == null && list.size() > 1 || list.isEmpty() )
            return null;
        BaseModel t = list.get(0);
        return t;
    }

    @Override
    public long save(BaseModel object) {
        SQLiteDatabase writableDatabase = new DatabaseHelper(context).getWritableDatabase();
        writableDatabase.beginTransaction();
        // todo resolver problema de factory
        ContentValues values = factory.toContentValues();
        values.remove(BaseColumns._ID);
        long id = writableDatabase.insert(tableName, null, values);

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
    public List search(String key, String value, int offset, int limit) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String selection = key + "=?";
        String[] selectionArgs = {value};
        String limitString = offset + "," + limit;
        Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null, limitString);

        return factory.getList(cursor);
    }

    @Override
    public List load(int offset, int limit) {
        SQLiteDatabase readableDatabase = new DatabaseHelper(context).getReadableDatabase();
        String limitString = offset + "," + limit;
        Cursor cursor = readableDatabase.query(tableName, null, null, null, null, null, null, limitString);

        return factory.getList(cursor);
    }

    @Override
    public int update(long id, ContentValues values) {
        SQLiteDatabase writableDatabase = new DatabaseHelper(context).getWritableDatabase();
        String where = BaseColumns._ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        int rowsUpdated = writableDatabase.update(tableName, values, where, whereArgs);
        return rowsUpdated;
    }
}
