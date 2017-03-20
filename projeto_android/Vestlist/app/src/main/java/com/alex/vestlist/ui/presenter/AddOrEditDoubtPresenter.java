package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;

import java.security.InvalidParameterException;

/**
 * Created by Alex on 19/03/2017.
 */

public class AddOrEditDoubtPresenter extends BaseAddOrEditPresenter<Doubt, AddOrEditDoubtPresenter.View> {

    public AddOrEditDoubtPresenter(View mView, Context mContext, Bundle savedInstanceState) {
        super(mView, mContext, savedInstanceState);
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
    protected void analiseBackgroundThreadResultData(Object result, TaskType taskType) {
        switch (taskType){
            case SAVE:
                Long dataId = (Long) result;
                if (dataId <= 0){
                    mView.showErrorMsg("Dúvida não foi salva");
                }else {
                    mView.showSuccessMsg("Dúvida adicionada");
                    mView.returnResultToActivity();
                }
                break;
            case REMOVE:
                boolean deleted = (boolean) result;
                if (!deleted){
                    mView.showErrorMsg("Dúvida não pode ser removida");
                }else {
                    mView.showSuccessMsg("Dúvida removida");
                    mView.returnResultToActivity();
                }
                break;
            case EDIT:
                int rowsUpdated = (int) result;
                if (rowsUpdated <= 0){
                    mView.showErrorMsg("Dúvida não pode ser editada");
                }else {
                    mView.showSuccessMsg("Dúvida editada");
                    mView.returnResultToActivity();
                }
                break;

        }
    }

    @Override
    public void validateDataInputToSaveOrEdit(Doubt data) {
        if (data == null || data.getListId() <= 0) {
            throw new NullPointerException("Dado para validar está nulo ou sem id de lista");
        }else if (data.getQuestion().isEmpty()) {
            mView.showInvalidInputError("Você deve informar a questão");
        }else
            startSaveOrEditDataInSource(data);
    }

    @Override
    public void validateDataToSetEditView(Doubt data) {
        if (data == null || data.getQuestion().isEmpty() || data.getId() <= 0 || data.getListId() <= 0)
            throw new InvalidParameterException("Dúvida não é válida para edição");
        else
            mView.setViewToEditData(data);
    }

    public interface View extends BaseAddOrEditContract.View<Doubt>{

    }
}
