package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.util.ViewUtil;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class ListPresenter extends BaseListPresenter<ListPresenter.View, ExerciseList>{

    public ListPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não listas");
    }

    @Override
    public List<ExerciseList> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadLists(mView.getTeacher(), offset, loadItemsLimit);
    }

    @Override
    public void populateFilteredList(String filterType) {

    }

    @Override
    public void showAddOrEditView(ExerciseList data) {
        mView.showNewListDialog();
    }

    @Override
    public int updateModelInSource(ExerciseList data) {
        return mSource.updateList(data);
    }

    @Override
    public void openDataDetails(ExerciseList item) {
        ViewUtil.showNotImplementedMsg(context);
    }


    public interface View<ModelType extends BaseModel> extends BaseListContract.View<ModelType> {
        public void showNewListDialog();

        public Teacher getTeacher();
    }

}
