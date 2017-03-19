package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.view.DoubtActivity;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class ListPresenter extends BaseListPresenter<ListPresenter.View, ExerciseList>{

    private boolean errorInBackground;

    public ListPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    protected void analiseBackgroundThreadResultData(Object result, TaskType taskType) {
        switch (taskType){
            case LOAD:
                List<ExerciseList> list = (List<ExerciseList>) result;
                if (list == null || list.isEmpty())
                    return;
                if (mOffset <= 0)
                    mView.createListAdapter(list);
                else
                    mView.addAdapterData(list);
                break;
            case SAVE:
                Long dataId = (Long) result;
                if (dataId <= 0){
                    mView.showErrorMsg("Lista não foi salva");
                }else {
                    mView.showSuccessMsg("Lista adicionada");
                    reCreateAdapter();
                }
                break;
            case REMOVE:
                boolean deleted = (boolean) result;
                if (!deleted){
                    mView.showErrorMsg("Lista não pode ser removida");
                }else {
                    mView.showSuccessMsg("Lista removida");
                    reCreateAdapter();
                }
                break;
            case EDIT:
                int rowsUpdated = (int) result;
                if (rowsUpdated <= 0){
                    mView.showErrorMsg("Lista não pode ser editada");
                }else {
                    mView.showSuccessMsg("Lista editada");
                    reCreateAdapter();
                }
                break;
            case FILTER_FROM_ADAPTER:
            case FILTER_FROM_SOURCE:
                list = (List<ExerciseList>) result;
                if (list == null || list.isEmpty())
                    return;
                else{
                    mView.createListAdapter(list);
                }
                break;

        }
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
    protected int updateDataFromSource(ExerciseList data) {
        return mSource.updateList(data);
    }

    @Override
    protected boolean removeDataFromSource(ExerciseList data) {
        return mSource.removeList(data.getId());
    }

    @Override
    protected Long saveDataFromSource(ExerciseList data) {
        return mSource.insertList(data);
    }

    @Override
    protected List<ExerciseList> applyFilterFromAdapter() {
        return null;
    }

    @Override
    protected List<ExerciseList> applyFilterFromSource() {
        return null;
    }

    @Override
    public void selectItemClicked(ExerciseList item) {
        Intent intent = new Intent(mContext, DoubtActivity.class);
        intent.putExtra(DoubtActivity.ARGUMENT_LIST_KEY, item);
        mContext.startActivity(intent);
    }

    @Override
    public void showAddOrEditView(ExerciseList data) {
        mView.showAddOrEditDataView(data);
    }


    public interface View extends BaseListContract.View<ExerciseList> {

        public Teacher getTeacher();
    }

}
