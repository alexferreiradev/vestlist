package com.alex.vestlist.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;

/**
 * Created by Alex on 16/03/2017.
 */

public class Doubt extends BaseModel<Doubt>{

    private long id;
    private String question;
    private String details;
    private long listId;

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        // id, question, details, list
        contentValues.put(BaseColumns._ID, id);
        contentValues.put(VestListContract.DoubtEntry.QUESTION, question);
        contentValues.put(VestListContract.DoubtEntry.DETAILS_COLLUNM, details);
        contentValues.put(VestListContract.DoubtEntry.FK_LIST_COLLUNM, listId);

        return contentValues;
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

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doubt doubt = (Doubt) o;

        if (id != doubt.id) return false;
        if (listId != doubt.listId) return false;
        if (!question.equals(doubt.question)) return false;
        return details != null ? details.equals(doubt.details) : doubt.details == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + question.hashCode();
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (int) (listId ^ (listId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Doubt{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", details='" + details + '\'' +
                ", listId=" + listId +
                '}';
    }
}
