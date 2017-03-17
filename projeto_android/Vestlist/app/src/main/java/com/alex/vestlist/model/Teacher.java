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

public class Teacher extends BaseModel<Teacher> {

    private long id;
    private String name;
    private Subject subject;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        // id, name, subject
        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.TeacherEntry.NAME_COLLUNM, name);
        contentValues.put(VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM, subject.getId());

        return contentValues;
    }

    @Override
    public Teacher convertCursor(Cursor cursor) {
        // id, name, subject
        int columnIndex = cursor.getColumnIndex(BaseColumns._ID);
        long id = cursor.getLong(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.TeacherEntry.NAME_COLLUNM);
        String name = cursor.getString(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM);
        long subjectId = cursor.getInt(columnIndex);

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        // TODO criar relacionamento, fazer carregamento do bd
        teacher.setSubject(null);
        return teacher;
    }

    @Override
    public List<Teacher> getList(Cursor cursor) {
        if (!cursor.moveToFirst())
            return null;

        List<Teacher> list = new ArrayList<>();
        do{
            Teacher teacher = convertCursor(cursor);
            list.add(teacher);
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        return subject != null ? subject.equals(teacher.subject) : teacher.subject == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                '}';
    }
}
