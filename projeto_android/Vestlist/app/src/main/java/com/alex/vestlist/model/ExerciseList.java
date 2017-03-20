package com.alex.vestlist.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;

/**
 * Created by Alex on 16/03/2017.
 */

public class ExerciseList extends BaseModel<ExerciseList> {

    private String name;
    private boolean completed;
    private boolean hasDoubt;
    private long teacherId;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.ListEntry.NAME_COLLUNM, name);
        contentValues.put(VestListContract.ListEntry.FK_TEACHER_COLLUNM, teacherId);
        if (completed)
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public boolean isHasDoubt() {
        return hasDoubt;
    }

    public void setHasDoubt(boolean hasDoubt) {
        this.hasDoubt = hasDoubt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseList that = (ExerciseList) o;

        if (completed != that.completed) return false;
        if (hasDoubt != that.hasDoubt) return false;
        if (teacherId != that.teacherId) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + (hasDoubt ? 1 : 0);
        result = 31 * result + (int) (teacherId ^ (teacherId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ExerciseList{" +
                "name='" + name + '\'' +
                ", completed=" + completed +
                ", hasDoubt=" + hasDoubt +
                ", teacherId=" + teacherId +
                '}';
    }
}
