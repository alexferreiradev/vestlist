package com.alex.vestlist.dao.util;

import android.database.Cursor;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

/**
 * Created by Alex on 18/03/2017.
 */

public interface BaseCursorModelUtil {

    public abstract BaseModel getModelFromCursor(Cursor cursor);

    public List getListFromCursor(Cursor cursor);

}
