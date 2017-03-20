package com.alex.vestlist.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;

/**
 * Created by Alex on 16/03/2017.
 */

public class Teacher extends BaseModel<Teacher> {

    private long id;
    private String name;
    private long subjectId;
    private Float listPercentage;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        // id, name, subject
        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.TeacherEntry.NAME_COLLUNM, name);
        contentValues.put(VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM, subjectId);

        return contentValues;
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

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public Float getListPercentage() {
        return listPercentage;
    }

    public void setListPercentage(Float listPercentage) {
        this.listPercentage = listPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (subjectId != teacher.subjectId) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        return listPercentage != null ? listPercentage.equals(teacher.listPercentage) : teacher.listPercentage == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (subjectId ^ (subjectId >>> 32));
        result = 31 * result + (listPercentage != null ? listPercentage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", listPercentage=" + listPercentage +
                '}';
    }
}
