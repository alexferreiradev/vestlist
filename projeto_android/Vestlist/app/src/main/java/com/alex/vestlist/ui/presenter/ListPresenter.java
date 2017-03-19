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

public class ListPresenter extends BaseListPresenter<ListPresenter.View, ExerciseList>{

    private boolean errorInBackground;

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
    public void startAddOrEditThread(ExerciseList data) {
        if (data == null || data.getName().isEmpty()){
            mView.showErrorMsg("voce deve escrever um nome com mais de 3 carac.");
            mView.showAddOrEditDataView();
            return ;
        }
        super.startAddOrEditThread(data);
    }

    @Override
    public void analiseSaveThreadResult(Long id) {
        if (errorInBackground)
            mView.showErrorMsg("Erro ao salvar ou editar");
        super.analiseSaveThreadResult(id);
    }

    @Override
    public void showAddOrEditView(ExerciseList data) {
        mView.showAddOrEditDataView();
    }

    @Override
    public int updateModelInSource(ExerciseList data) {
        return mSource.updateList(data);
    }

    //TODO colocar no BaseContract
    public long saveModelInSource(ExerciseList data){
        try {
            return mSource.insertList(data);
        } catch (Exception e){
            errorInBackground = true;
        }
        return -1;
    }

    @Override
    protected void showSuccessMsg() {
        mView.showSuccessMsg("Operacao realizada com sucesso");
    }

    @Override
    protected void showErrorMsg() {
        mView.showErrorMsg("Operacao realizada com erro");
    }

    @Override
    public void openDataDetails(ExerciseList item) {
        ViewUtil.showNotImplementedMsg(context);
    }


    public interface View extends BaseListContract.View<ExerciseList> {

        public Teacher getTeacher();
    }

}
