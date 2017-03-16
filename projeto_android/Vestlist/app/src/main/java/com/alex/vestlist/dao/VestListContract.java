package com.alex.vestlist.dao;

import android.provider.BaseColumns;

/**
 * Created by Alex on 16/03/2017.
 */

public class VestListContract {

    public class ListEntry implements BaseColumns{
        public final static String NAME_COLLUNM = "name";
        public final static String STATUS_COLLUNM = "status";
        public final static String FK_TEACHER_COLLUNM = "fk_teacher";
    }

    public class TeacherEntry implements BaseColumns{
        public final static String NAME_COLLUNM = "name";
        public final static String FK_SUBJECT_COLLUNM = "fk_subject";
    }

    public class DoutEntry implements BaseColumns{
        public final static String question = "question";
        public final static String DETAILS_COLLUNM = "details";
        public final static String FK_LIST_COLLUNM = "fk_list";
    }

    public class SubjectEntry implements BaseColumns{
        public final static String NAME_COLLUNM = "name";
    }
}
