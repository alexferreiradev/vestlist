package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.model.ExerciseList;

/**
 * Created by Alex on 17/03/2017.
 */

public class ListPresenter extends BaseListInfoPresenter<ExerciseList> {

    public ListPresenter(ListPresenter.View mView, Context context) {
        super(mView, context);
    }

    @Override
    public void onUpdateListActionSelected() {

    }

    @Override
    public void onAddActionSelected() {
        mView.show
    }

    public interface View extends BaseListInfoPresenter.View<ExerciseList> {
        public void showNewListDialog();
    }

}
