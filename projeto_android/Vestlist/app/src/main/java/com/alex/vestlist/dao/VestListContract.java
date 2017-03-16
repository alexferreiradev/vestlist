package com.alex.vestlist.dao;

import android.provider.BaseColumns;

/**
 * Created by Alex on 16/03/2017.
 */

public class VestListContract {

    private VestListContract(){}

    public class ListEntry implements BaseColumns{

        public final static String TABLE_NAME= "list";
        public final static String NAME_COLLUNM = "name";
        public final static String STATUS_COLLUNM = "status";
        public final static String FK_TEACHER_COLLUNM = "fk_teacher";
    }

    public class TeacherEntry implements BaseColumns{
        public final static String TABLE_NAME= "teacher";
        public final static String NAME_COLLUNM = "name";
        public final static String FK_SUBJECT_COLLUNM = "fk_subject";
    }

    public class DoubtEntry implements BaseColumns{
        public final static String TABLE_NAME= "doubt";
        public final static String QUESTION = "question";
        public final static String DETAILS_COLLUNM = "details";
        public final static String FK_LIST_COLLUNM = "fk_list";
    }

    public class SubjectEntry implements BaseColumns{
        public final static String TABLE_NAME= "subject";
        public final static String NAME_COLLUNM = "name";
    }
}
