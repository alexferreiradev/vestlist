package com.alex.vestlist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class Subject extends BaseModel<Subject>{

    private long id;
    private String name;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        // id, name
        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.SubjectEntry.NAME_COLLUNM, name);

        return contentValues;
    }

    @Override
    public Subject convertCursor(Cursor cursor) {
        // id, name
        int columnIndex = cursor.getColumnIndex(BaseColumns._ID);
        long id = cursor.getLong(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.SubjectEntry.NAME_COLLUNM);
        String name = cursor.getString(columnIndex);

        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        return subject;
    }

    @Override
    public List<Subject> getList(Cursor cursor) {
        if (!cursor.moveToFirst())
            return null;

        List<Subject> list = new ArrayList<>();
        do{
            Subject subject = convertCursor(cursor);
            list.add(subject);
        }while (cursor.moveToNext());

        return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != subject.id) return false;
        return name != null ? name.equals(subject.name) : subject.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
