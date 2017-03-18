package com.alex.vestlist.dao.util;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.alex.vestlist.dao.VestListContract;
import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 18/03/2017.
 */

public enum CursorModelUtil implements BaseCursorModelUtil{

    /**
     * Models: Subject, Teacher, Doubt, List
     */
    SUBJECT {
        @Override
        public List<Subject> getListFromCursor(Cursor cursor) {
            return super.getListFromCursor(cursor);
        }

        @Override
        public BaseModel getModelFromCursor(Cursor cursor) {
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
    },
    TEACHER{
        @Override
        public List<Teacher> getListFromCursor(Cursor cursor) {
            return super.getListFromCursor(cursor);
        }

        @Override
        public BaseModel getModelFromCursor(Cursor cursor) {
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
            teacher.setSubjectId(subjectId);
            return teacher;
        }
    },
    DOUBT{
        @Override
        public List<Doubt> getListFromCursor(Cursor cursor) {
            return super.getListFromCursor(cursor);
        }

        @Override
        public BaseModel getModelFromCursor(Cursor cursor) {
            // id, question, details, list
            int columnIndex = cursor.getColumnIndex(BaseColumns._ID);
            long id = cursor.getLong(columnIndex);
            columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.QUESTION);
            String question = cursor.getString(columnIndex);
            columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.DETAILS_COLLUNM);
            String details = cursor.getString(columnIndex);
            columnIndex = cursor.getColumnIndex(VestListContract.DoubtEntry.FK_LIST_COLLUNM);
            long listId = cursor.getInt(columnIndex);

            Doubt doubt = new Doubt();
            doubt.setId(id);
            doubt.setQuestion(question);
            doubt.setDetails(details);
            doubt.setListId(listId);

            return doubt;
        }
    },
    LIST{
        @Override
        public List<ExerciseList> getListFromCursor(Cursor cursor) {
            return super.getListFromCursor(cursor);
        }

        @Override
        public BaseModel getModelFromCursor(Cursor cursor) {
            // id, name, status, teacher
            int columnIndex = cursor.getColumnIndex(BaseColumns._ID);
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
            if (status == 1)
                exerciseList.setStatus(true);
            else
                exerciseList.setStatus(false);
            exerciseList.setTeacherId(teacherId);
            return exerciseList;
        }
    }
    ;

    public List getListFromCursor(Cursor cursor){
        if (!cursor.moveToFirst())
            return null;

        List<BaseModel> list = new ArrayList<>();
        do{
            BaseModel model = getModelFromCursor(cursor);
            list.add(model);
        }while (cursor.moveToNext());

        return list;
    }

    public abstract BaseModel getModelFromCursor(Cursor cursor);
}
