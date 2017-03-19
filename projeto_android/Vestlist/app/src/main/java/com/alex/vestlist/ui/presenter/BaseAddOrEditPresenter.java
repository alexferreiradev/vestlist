package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;

/**
 * Created by Alex on 19/03/2017.
 */

public abstract class BaseAddOrEditPresenter<ModelType extends BaseModel,
        ViewType extends BaseAddOrEditContract.View>
        extends BasePresenter<BaseAddOrEditContract.View, ModelType> implements BaseAddOrEditContract.Presenter<ModelType>{

    public BaseAddOrEditPresenter(ViewType mView, Context mContext, Bundle savedInstanceState) {
        super(mView, mContext, savedInstanceState);
    }

    protected abstract int updateDataFromSource(ModelType data);

    protected abstract boolean removeDataFromSource(ModelType data);

    protected abstract Long saveDataFromSource(ModelType data);

    @Override
    public Object taskFromSource(ModelType data, TaskType taskType) {
        switch (taskType){
            case EDIT:
                return updateDataFromSource(data);
            case SAVE:
                return saveDataFromSource(data);
            case REMOVE:
                return removeDataFromSource(data);
        }
        return null;
    }

    protected void returnResultToActivity(){
        mView.returnResultToActivity();
    }

    @Override
    protected void initialize() {

    }

}
