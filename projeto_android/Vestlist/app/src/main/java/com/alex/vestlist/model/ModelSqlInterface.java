package com.alex.vestlist.model;

import android.content.ContentValues;

/**
 * Created by Alex on 16/03/2017.
 */

public interface ModelSqlInterface<T> {

    public ContentValues toContentValues();
}
