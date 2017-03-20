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
        List<Teacher> allTeacher = teacherCrud.search(VestListContract.TeacherEntry.FK_SUBJECT_COLLUNM, String.valueOf(subject.getId()), offset, limit);
        if (allTeacher == null)
            return null;

        for (Teacher teacher : allTeacher) {
            teacher.setListPercentage(new Float(getListCompletedPercent(teacher)));
        }

        return allTeacher;
    }

    @Override
    public List loadDoubts(ExerciseList list, int offset, int limit) {
        return doubtCrud.search(VestListContract.DoubtEntry.FK_LIST_COLLUNM, String.valueOf(list.getId()), offset, limit);
    }

    @Override
    public List loadLists(Teacher teacher, int offset, int limit) {
        List<ExerciseList> allList = exerciseListCrud.search(VestListContract.ListEntry.FK_TEACHER_COLLUNM
                , String.valueOf(teacher.getId()), offset, limit);
        if (allList == null)
            return null;

        for (ExerciseList exerciseList : allList) {
            List list = loadDoubts(exerciseList, 0, 1);
            if (list != null && !list.isEmpty()){
                exerciseList.setHasDoubt(true);
                continue;
            }
        }

//        exerciseListCrud.getExerciseListWithDoubt(offset, limit, teacher.getId());

        return allList;
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
    public double getListCompletedPercent(Teacher teacher) {
        List<ExerciseList> lists = exerciseListCrud.search(VestListContract.ListEntry.FK_TEACHER_COLLUNM, String.valueOf(teacher.getId()), 0, Integer.MAX_VALUE);
        if (lists == null)
            return 0;

        int total = lists.size();
        int completed = 0;
        for (ExerciseList list : lists) {
            if (list.isCompleted())
                completed ++;
        }

        return completed*1.0/total*100;
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
