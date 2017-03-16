package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class TeacherPresenter extends Presenter<TeacherPresenter.View>{

    public TeacherPresenter(View view, Context context) {
        super(view, context);
    }

    @Override
    public void initialize() {
        super.initialize();
        List teachers = mBussiness.loadTeachers(0, -1);
        mView.showTeachers(teachers);
    }

    public interface View extends Presenter.View {
        public void showTeachers(List<Teacher> teachers);
    }
}
