package com.alex.vestlist.source;

import android.content.Context;

import com.alex.vestlist.dao.SqlSourceDao;
import com.alex.vestlist.dao.VestListContract;
import com.alex.vestlist.dao.util.CursorModelUtil;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class StudentSource implements StudentSourceContract {

    private SqlSourceDao<Subject> subjectCrud;
    private SqlSourceDao<Teacher> teacherCrud;
    private SqlSourceDao<ExerciseList> exerciseListCrud;
    private SqlSourceDao<Doubt> doubtCrud;

    public StudentSource(Context context) {
        subjectCrud = new SqlSourceDao<>(context, VestListContract.SubjectEntry.TABLE_NAME, CursorModelUtil.SUBJECT);
        teacherCrud = new SqlSourceDao<>(context, VestListContract.TeacherEntry.TABLE_NAME, CursorModelUtil.TEACHER);
        exerciseListCrud = new SqlSourceDao<>(context, VestListContract.ListEntry.TABLE_NAME, CursorModelUtil.LIST);
        doubtCrud = new SqlSourceDao<>(context, VestListContract.DoubtEntry.TABLE_NAME, CursorModelUtil.DOUBT);
    }

    @Override
    public List<Subject> loadSubjects(int offset, int limit) {
        return subjectCrud.load(offset, limit);
    }

    @Override
    public List loadTeachers(Subject subject, int offset, int limit) {
        return teacherCrud.search(VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM, String.valueOf(subject.getId()), offset, limit);
    }

    @Override
    public List loadDoubts(ExerciseList list, int offset, int limit) {
        return doubtCrud.search(VestListContract.DoubtEntry.FK_LIST_COLLUNM, String.valueOf(list.getId()), offset, limit);
    }

    @Override
    public List loadLists(Teacher teacher, int offset, int limit) {
//        exerciseListCrud.search(VestListContract.ListEntry.FK_TEACHER_COLLUNM
//                , String.valueOf(teacher.getId()), offset, limit);
        return exerciseListCrud.getExerciseListWithDoubt(offset, limit, teacher.getId());
    }

    @Override
    public boolean removeList(long id) {
        int deleteRows = exerciseListCrud.delete(id);
        if (deleteRows <=0 )
            return false;

        return true;
    }

    @Override
    public boolean removeDoubt(long id) {
        int deleteRows = doubtCrud.delete(id);
        if (deleteRows <=0 )
            return false;

        return true;
    }

    @Override
    public long insertList(ExerciseList list) {
        return exerciseListCrud.save(list);
    }

    @Override
    public long insertDoubt(Doubt doubt) {
        return doubtCrud.save(doubt);
    }

    @Override
    public float getListCompletedPercent(Teacher teacher) {
        List<ExerciseList> lists = exerciseListCrud.search(VestListContract.ListEntry.FK_TEACHER_COLLUNM, String.valueOf(teacher.getId()), 0, -1);
        int total = lists.size();
        List<ExerciseList> completedLists = exerciseListCrud.search(VestListContract.ListEntry.STATUS_COLLUNM, "1", 0, -1);
        int totalCompletedLists = completedLists.size();
        return totalCompletedLists/total;
    }

    @Override
    public int updateDoubt(Doubt doubt) {
        return doubtCrud.update(doubt.getId(), doubt.toContentValues());
    }

    @Override
    public int updateList(ExerciseList list) {
        return exerciseListCrud.update(list.getId(), list.toContentValues());
    }
}
