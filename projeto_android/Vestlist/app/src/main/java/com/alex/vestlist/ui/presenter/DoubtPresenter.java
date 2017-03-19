package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.ui.util.ViewUtil;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class DoubtPresenter extends BaseListPresenter<DoubtPresenter.View, Doubt> {

    public DoubtPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    public List<Doubt> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadDoubts(mView.getExerciseList(), offset, loadItemsLimit);
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há dúvidas");
    }

    @Override
    protected void showSuccessMsg() {
        // TODO
    }

    @Override
    protected void showErrorMsg() {
        // TODO
    }

    @Override
    public void populateFilteredList(String filterType) {
        // TODO
    }

    @Override
    public void showAddOrEditView(Doubt data) {
        // TODO
    }

    @Override
    public int updateModelInSource(Doubt data) {
        return mSource.updateDoubt(data);
    }

    @Override
    public void selectItemClicked(Doubt item) {
        ViewUtil.showNotImplementedMsg(mContext);
    }

    public interface View extends BaseListContract.View<Doubt>{

        public ExerciseList getExerciseList();

    }
}
