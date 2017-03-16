package com.alex.vestlist.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.alex.vestlist.dao.VestListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class ExerciseList implements ModelSqlInterface<ExerciseList> {

    private long id;
    private String name;
    private boolean status;

    private Teacher teacher;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(VestListContract.ListEntry._ID, id);
        contentValues.put(VestListContract.ListEntry.NAME_COLLUNM, name);
        contentValues.put(VestListContract.ListEntry.FK_TEACHER_COLLUNM, teacher.getId());
        if (status)
            contentValues.put(VestListContract.ListEntry.STATUS_COLLUNM, 1);
        else
            contentValues.put(VestListContract.ListEntry.STATUS_COLLUNM, 0);

        return contentValues;
    }

    @Override
    public ExerciseList convertCursor(Cursor cursor) {
        if (!cursor.moveToFirst())
            return null;

        // id, name, status, teacher
        int columnIndex = cursor.getColumnIndex(VestListContract.ListEntry._ID);
        long id = cursor.getLong(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.ListEntry.NAME_COLLUNM);
        String name = cursor.getString(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.ListEntry.STATUS_COLLUNM);
        int status = cursor.getInt(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.ListEntry.FK_TEACHER_COLLUNM);
        long teacherId = cursor.getInt(columnIndex);

        ExerciseList exerciseList = new ExerciseList();
        exerciseList.setId(id);
        exerciseList.setName(name);
        // TODO criar relacionamento, fazer carregamento do bd
        exerciseList.setTeacher(null);
        return exerciseList;
    }

    @Override
    public List<ExerciseList> getList(Cursor cursor) {
        if (!cursor.moveToFirst())
            return null;

        List<ExerciseList> list = new ArrayList<>();
        do{
            ExerciseList e = convertCursor(cursor);
            list.add(e);
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseList that = (ExerciseList) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return teacher != null ? teacher.equals(that.teacher) : that.teacher == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExerciseList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", teacher=" + teacher +
                '}';
    }
}
