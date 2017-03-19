package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;

/**
 * Created by Alex on 19/03/2017.
 */

public class AddOrEditDoubtPresenter extends BaseAddOrEditPresenter<Doubt, AddOrEditDoubtPresenter.View> {

    public AddOrEditDoubtPresenter(View mView, Context mContext, Bundle savedInstanceState) {
        super(mView, mContext, savedInstanceState);
    }

    @Override
    protected void initialize() {
        // TODO start teclado no primeiro ETV
    }

    @Override
    public void validateData(Doubt data) {
        mView.toggleProgressBar();
        if (data == null) {
            mView.toggleProgressBar();
            throw new NullPointerException("Dado para validar está nulo");
        }else if (data.getQuestion().isEmpty() ) {
            mView.toggleProgressBar();
            mView.showInvalidInputError("Você deve informar a questão");
        }else
            mView.startAddOrUpdateThread();
    }

    @Override
    public void analiseResultFromThread(Long result) {
        mView.toggleProgressBar();
        if (result <= 0)
            mView.showErrorMsg("Erro ao salvar dado");
        else{
            mView.showSuccessMsg("Dúvida salva");
            returnResultToActivity();
        }
    }

    public interface View extends BaseAddOrEditContract.View{

    }
}
