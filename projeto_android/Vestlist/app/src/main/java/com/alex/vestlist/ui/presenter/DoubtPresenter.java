package com.alex.vestlist.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.ui.view.AddOrEditDoubtActivity;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class DoubtPresenter extends BaseListPresenter<DoubtPresenter.View, Doubt> {

    private static final int ADD_DOUBT_REQUEST_CODE = 0;

    public DoubtPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    protected void showDataNotEditedError() {
        mView.showErrorMsg("Dúvida não foi editada");
    }

    @Override
    protected void showDataEditedSuccess() {
        mView.showSuccessMsg("Dúvida foi editada");
    }

    @Override
    protected void showDataSavedSuccess() {
        mView.showSuccessMsg("Dúvida foi adicionada");
    }

    @Override
    protected void showDataRemovedError() {
        mView.showErrorMsg("Dúvida não foi removida");
    }

    @Override
    protected void showDataRemovedSuccess() {
        mView.showSuccessMsg("Dúvida foi removida");
    }

    @Override
    protected void showDataNotSavedError() {
        mView.showErrorMsg("Dúvida não foi adicionada");
    }

    @Override
    public List<Doubt> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadDoubts(mView.getExerciseList(), offset, loadItemsLimit);
    }

    @Override
    protected int updateDataFromSource(Doubt data) {
        return mSource.updateDoubt(data);
    }

    @Override
    protected boolean removeDataFromSource(Doubt data) {
        return mSource.removeDoubt(data.getId());
    }

    @Override
    protected Long saveDataFromSource(Doubt data) {
        return mSource.insertDoubt(data);
    }

    @Override
    protected List<Doubt> applyFilterFromAdapter() {
        return null;
    }

    @Override
    protected List<Doubt> applyFilterFromSource() {
        return null;
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há dúvidas");
    }

    @Override
    public void showAddOrEditView(Doubt data) {
        Intent intent = new Intent(mContext, AddOrEditDoubtActivity.class);
        intent.putExtra(AddOrEditDoubtActivity.ARGUMENT_LIST_ID_KEY, data.getListId());

        if (data != null && data.getId() > 0){
            intent.putExtra(AddOrEditDoubtActivity.ARGUMENT_DOUBT_KEY, data);
        }
        ((Activity)mContext).startActivityForResult(intent, ADD_DOUBT_REQUEST_CODE);
    }

    @Override
    public void selectItemClicked(Doubt item) {
        showAddOrEditView(item);
    }

    public interface View extends BaseListContract.View<Doubt>{

        public ExerciseList getExerciseList();

    }
}
