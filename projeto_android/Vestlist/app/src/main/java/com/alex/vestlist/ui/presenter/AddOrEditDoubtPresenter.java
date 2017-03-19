package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;

import java.util.List;

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
    protected void initialize() {
        // TODO start teclado no primeiro ETV
    }

    @Override
    public void analiseBackgroundThreadResult(Object result, TaskType taskType) {
        mView.toggleProgressBar();
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
                int rowUpdated = (int) result;
                if (rowUpdated <= 0){
                    mView.showErrorMsg("Dúvida não pode ser removida");
                }else {
                    mView.showSuccessMsg("Dúvida removida");
                    mView.returnResultToActivity();
                }
                break;
            case EDIT:
                rowUpdated = (int) result;
                if (rowUpdated <= 0){
                    mView.showErrorMsg("Dúvida não pode ser editada");
                }else {
                    mView.showSuccessMsg("Dúvida editada");
                    mView.returnResultToActivity();
                }
                break;

        }
    }

    @Override
    public void validateData(Doubt data) {
        mView.toggleProgressBar();
        if (data == null) {
            mView.toggleProgressBar();
            throw new NullPointerException("Dado para validar está nulo");
        }else if (data.getQuestion().isEmpty() || data.getListId() <= 0) {
            mView.toggleProgressBar();
            mView.showInvalidInputError("Você deve informar a questão");
        }else
            startBackgroundThread(data, TaskType.SAVE);
    }

    public interface View extends BaseAddOrEditContract.View<Doubt>{

    }
}
