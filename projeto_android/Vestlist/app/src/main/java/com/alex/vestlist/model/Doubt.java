package com.alex.vestlist.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.alex.vestlist.dao.VestListContract;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class Doubt implements ModelSqlInterface<Doubt>{

    private long id;
    private String question;
    private String details;
    private ExerciseList list;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        // id, question, details, list
        contentValues.put(VestListContract.ListEntry._ID, id);
        contentValues.put(VestListContract.DoubtEntry.QUESTION, question);
        contentValues.put(VestListContract.DoubtEntry.DETAILS_COLLUNM, details);
        contentValues.put(VestListContract.DoubtEntry.FK_LIST_COLLUNM, list.getId());

        return contentValues;
    }

    @Override
    public Doubt convertCursor(Cursor cursor) {
        if (!cursor.moveToFirst())
            return null;

        // id, question, details, list
        int columnIndex = cursor.getColumnIndex(VestListContract.ListEntry._ID);
        long id = cursor.getLong(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.QUESTION);
        String name = cursor.getString(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.STATUS_COLLUNM);
        int status = cursor.getInt(columnIndex);
        columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.FK_TEACHER_COLLUNM);
        long teacherId = cursor.getInt(columnIndex);

        Doubt doubt = new Doubt();
        doubt.setId(id);
        doubt.setName(name);
        // TODO criar relacionamento, fazer carregamento do bd
        doubt.setTeacher(null);
        return doubt;
    }

    @Override
    public List<Doubt> getList(Cursor cursor) {
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ExerciseList getList() {
        return list;
    }

    public void setList(ExerciseList list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doubt doubt = (Doubt) o;

        if (id != doubt.id) return false;
        if (question != null ? !question.equals(doubt.question) : doubt.question != null)
            return false;
        if (details != null ? !details.equals(doubt.details) : doubt.details != null) return false;
        return list != null ? list.equals(doubt.list) : doubt.list == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Doubt{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", details='" + details + '\'' +
                ", list=" + list +
                '}';
    }

}
