package com.alex.vestlist.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.ui.util.ViewUtil;
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
    protected void analiseBackgroundThreadResultData(Object result, TaskType taskType) {
        switch (taskType){
            case LOAD:
                List<Doubt> list = (List<Doubt>) result;
                if (list == null || list.isEmpty())
                    return;
                if (mView.getAdapter() == null)
                    mView.createListAdapter(list);
                else
                    mView.addAdapterData(list);
                break;
            case SAVE:
                Long dataId = (Long) result;
                if (dataId <= 0){
                    mView.showErrorMsg("Dúvida não foi salva");
                }else {
                    mView.showSuccessMsg("Dúvida adicionada");
                    reCreateAdapter();
                }
                break;
            case REMOVE:
                int rowUpdated = (int) result;
                if (rowUpdated <= 0){
                    mView.showErrorMsg("Dúvida não pode ser removida");
                }else {
                    mView.showSuccessMsg("Dúvida removida");
                    reCreateAdapter();
                }
                break;
            case EDIT:
                rowUpdated = (int) result;
                if (rowUpdated <= 0){
                    mView.showErrorMsg("Dúvida não pode ser editada");
                }else {
                    mView.showSuccessMsg("Dúvida editada");
                    reCreateAdapter();
                }
                break;
            case FILTER_FROM_ADAPTER:
            case FILTER_FROM_SOURCE:
                list = (List<Doubt>) result;
                if (list == null || list.isEmpty())
                    return;
                else{
                    mView.createListAdapter(list);
                }
                break;

        }
    }

    @Override
    public List<Doubt> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadDoubts(mView.getExerciseList(), offset, loadItemsLimit);
    }

    @Override
    protected int updateDataFromSource(Doubt data) {
        return 0;
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
        ViewUtil.showNotImplementedMsg(mContext);
    }

    public interface View extends BaseListContract.View<Doubt>{

        public ExerciseList getExerciseList();

    }
}
