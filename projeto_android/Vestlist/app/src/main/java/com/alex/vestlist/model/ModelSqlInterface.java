package com.alex.vestlist.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public interface ModelSqlInterface<T> {

    public ContentValues toContentValues();

    public T convertCursor(Cursor cursor);

    public List<T> getList(Cursor cursor);
}
