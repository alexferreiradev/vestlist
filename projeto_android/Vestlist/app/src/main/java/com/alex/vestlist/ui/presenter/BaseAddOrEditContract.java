package com.alex.vestlist.ui.presenter;

import com.alex.vestlist.model.BaseModel;

/**
 * Created by Alex on 19/03/2017.
 */

interface BaseAddOrEditContract {

    public interface View<ModelType extends BaseModel> extends BasePresenter.View<ModelType>{

        public void showInvalidInputError(String msg);

        public void returnResultToActivity();

        public void setViewToEditData(ModelType data);
    }

    public interface Presenter<ModelType extends BaseModel> {

        public abstract void validateData(ModelType data);

        public void validateDataToEdit(ModelType data);

    }

}
