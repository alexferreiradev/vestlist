package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.util.ViewUtil;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class ListPresenter extends BaseListInfoPresenter<ListPresenter.View, ExerciseList> {

    public ListPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    public List<ExerciseList> requestLoadInfo(int offset, int loadItemsLimit) {
        return mBussiness.loadLists(mView.getTeacher(), offset, loadItemsLimit);
    }

    @Override
    public void onAddActionSelected() {
        mView.showNewListDialog();
    }

    @Override
    public void onItemSelected(Context context, ExerciseList item) {
        ViewUtil.showNotImplementedMsg(context);
    }

    @Override
    public void onAddedListViewItem() {
        ViewUtil.showNotImplementedMsg(context);
    }

    public interface View extends BaseListInfoPresenter.View {
        public void showNewListDialog();

        public Teacher getTeacher();
    }

}
