package com.alex.vestlist.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;

/**
 * Created by Alex on 16/03/2017.
 */

public class ExerciseList extends BaseModel<ExerciseList> {

    private String name;
    private boolean status;
    private long teacherId;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.ListEntry.NAME_COLLUNM, name);
        contentValues.put(VestListContract.ListEntry.FK_TEACHER_COLLUNM, teacherId);
        if (status)
            contentValues.put(VestListContract.ListEntry.STATUS_COLLUNM, 1);
        else
            contentValues.put(VestListContract.ListEntry.STATUS_COLLUNM, 0);

        return contentValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseList that = (ExerciseList) o;

        if (status != that.status) return false;
        if (teacherId != that.teacherId) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (int) (teacherId ^ (teacherId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ExerciseList{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", teacherId=" + teacherId +
                '}';
    }
}
