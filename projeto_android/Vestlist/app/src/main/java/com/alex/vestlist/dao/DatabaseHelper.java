package com.alex.vestlist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;

/**
 * Created by Alex on 16/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "vestlist.db";

    public static final String CREATE_TABLE_SQL_STRING = "CREATE TABLE ";
    public static final String PRIMARY_KEY_SQL_STRING = " integer primary key autoincrement";
    public static final String FOREIGN_KEY_SQL_STRING = " Foreign key (";
    public static final String REFERENCES_SQL_STRING = ") references ";
    public static final String DROP_TABLE_IF_EXISTS_SQl_STRING = "Drop table if exists ";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String []createTableStrings =
                {CREATE_TABLE_SQL_STRING + VestListContract.ListEntry.TABLE_NAME + " ( " +
                VestListContract.ListEntry.NAME_COLLUNM + " varchar(45) not null, " +
                VestListContract.ListEntry.STATUS_COLLUNM + " integer(1), " +
                VestListContract.ListEntry.FK_TEACHER_COLLUNM + " integer, " +
                VestListContract.ListEntry._ID + PRIMARY_KEY_SQL_STRING + ", " +
                FOREIGN_KEY_SQL_STRING + VestListContract.ListEntry.FK_TEACHER_COLLUNM + REFERENCES_SQL_STRING +
                VestListContract.TeacherEntry.TABLE_NAME + "("+BaseColumns._ID+"))",

                CREATE_TABLE_SQL_STRING + VestListContract.TeacherEntry.TABLE_NAME + " ( " +
                VestListContract.TeacherEntry.NAME_COLLUNM + " varchar(45) not null, " +
                VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM + " integer not null, " +
                VestListContract.TeacherEntry._ID + PRIMARY_KEY_SQL_STRING + ", " +
                FOREIGN_KEY_SQL_STRING + VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM + REFERENCES_SQL_STRING +
                VestListContract.SubjectEntry.TABLE_NAME + "("+BaseColumns._ID+"))",

                CREATE_TABLE_SQL_STRING + VestListContract.DoubtEntry.TABLE_NAME + " ( " +
                VestListContract.DoubtEntry.QUESTION + " varchar(45) not null, " +
                VestListContract.DoubtEntry.DETAILS_COLLUNM + " text not null, " +
                VestListContract.DoubtEntry.FK_LIST_COLLUNM + " integer not null, " +
                VestListContract.DoubtEntry._ID + PRIMARY_KEY_SQL_STRING + ", " +
                FOREIGN_KEY_SQL_STRING + VestListContract.DoubtEntry.FK_LIST_COLLUNM + REFERENCES_SQL_STRING +
                VestListContract.ListEntry.TABLE_NAME + "("+BaseColumns._ID+"))",

                CREATE_TABLE_SQL_STRING + VestListContract.SubjectEntry.TABLE_NAME + " ( " +
                VestListContract.SubjectEntry.NAME_COLLUNM + " varchar(45) not null, " +
                VestListContract.SubjectEntry._ID + PRIMARY_KEY_SQL_STRING + " )"};

        for (String sql : createTableStrings){
            db.execSQL(sql);
        }
        createFixedValues(db);
    }

    /**
     * Cadastra dados fixos no banco.
     *
     * Dados de matérias e professores.
     * @param db
     */
    private void createFixedValues(SQLiteDatabase db) {
        db.beginTransaction();
        String []subjectNames = {"Matemática", "Português"};
        String []teacherNames = {"João", "José"};

        for (String name : subjectNames) {
            Subject subject = new Subject();
            subject.setName(name);
            ContentValues values = subject.toContentValues();
            values.remove(BaseColumns._ID);
            long insert = db.insert(VestListContract.SubjectEntry.TABLE_NAME, null, values);
            subject.setId(insert);
            for (String teacherName : teacherNames){
                Teacher teacher = new Teacher();
                teacher.setSubject(subject);
                teacher.setName(teacherName);
                values = teacher.toContentValues();
                values.remove(BaseColumns._ID);
                insert = db.insert(VestListContract.TeacherEntry.TABLE_NAME, null, values);
                teacher.setId(insert);
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTableStrings[] =
                {DROP_TABLE_IF_EXISTS_SQl_STRING + VestListContract.DoubtEntry.TABLE_NAME + "; ",
                DROP_TABLE_IF_EXISTS_SQl_STRING + VestListContract.ListEntry.TABLE_NAME + "; ",
                DROP_TABLE_IF_EXISTS_SQl_STRING + VestListContract.TeacherEntry.TABLE_NAME + "; ",
                DROP_TABLE_IF_EXISTS_SQl_STRING + VestListContract.SubjectEntry.TABLE_NAME + "; "};

        db.beginTransaction();
        for (String sql : deleteTableStrings){
            db.execSQL(sql);
        }
        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
