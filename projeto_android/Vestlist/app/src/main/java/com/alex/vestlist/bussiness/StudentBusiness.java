package com.alex.vestlist.bussiness;

import android.content.Context;

import com.alex.vestlist.dao.CrudImpl;
import com.alex.vestlist.dao.VestListContract;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class StudentBusiness implements StudentBusInterface {

    private CrudImpl<Subject> subjectCrud;
    private CrudImpl<Teacher> teacherCrud;
    private CrudImpl<ExerciseList> exerciseListCrud;
    private CrudImpl<Doubt> doubtCrud;

    public StudentBusiness(Context context) {
        subjectCrud = new CrudImpl<>(context, VestListContract.SubjectEntry.TABLE_NAME, new Subject());
        teacherCrud = new CrudImpl<>(context, VestListContract.TeacherEntry.TABLE_NAME, new Teacher());
        exerciseListCrud = new CrudImpl<>(context, VestListContract.ListEntry.TABLE_NAME, new ExerciseList());
        doubtCrud = new CrudImpl<>(context, VestListContract.DoubtEntry.TABLE_NAME, new Doubt());
    }

    @Override
    public List<Subject> loadSubjects(int offset, int limit) {
        return subjectCrud.load(offset, limit);
    }

    @Override
    public List loadTeachers(int offset, int limit) {
        return teacherCrud.load(offset, limit);
    }

    @Override
    public List loadDoubts(int offset, int limit) {
        return doubtCrud.load(offset, limit);
    }

    @Override
    public List loadLists(Teacher teacher, int offset, int limit) {
        return exerciseListCrud.search(VestListContract.ListEntry.FK_TEACHER_COLLUNM, String.valueOf(teacher.getId()), offset, limit);
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
    public long updateDoubt(Doubt doubt) {
        return doubtCrud.update(doubt.getId(), doubt.toContentValues());
    }

    @Override
    public long updateList(ExerciseList list) {
        return exerciseListCrud.update(list.getId(), list.toContentValues());
    }
}
