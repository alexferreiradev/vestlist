package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class ListPresenter extends Presenter<ListPresenter.View> {

    private Teacher teacher;

    public ListPresenter(View mView, Context context, Teacher teacher) {
        super(mView, context);
        this.teacher = teacher;
    }

    @Override
    public void initialize() {
        super.initialize();
//        mBussiness.loadLists(teacher, 0, -1)
    }

    public interface View extends Presenter.View{
        public void showLists(List<ExerciseList> lists);
    }
}
