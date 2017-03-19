package com.alex.vestlist.ui.presenter;

import com.alex.vestlist.model.BaseModel;

/**
 * Created by Alex on 19/03/2017.
 */

interface BaseAddOrEditContract {

    public interface View<ModelType extends BaseModel> extends BasePresenter.View{

        public void startAddOrUpdateThread(ModelType data);

        public void showInvalidInputError(String msg);

        public void returnResultToActivity();
    }

    public interface Presenter<ModelType extends BaseModel> {

        public abstract void validateData(ModelType data);

    }

}
