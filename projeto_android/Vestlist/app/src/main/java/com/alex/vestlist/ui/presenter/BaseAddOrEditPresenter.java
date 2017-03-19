package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;

/**
 * Created by Alex on 19/03/2017.
 */

public abstract class BaseAddOrEditPresenter<ModelType extends BaseModel,
        ViewType extends BaseAddOrEditContract.View>
        extends BasePresenter<BaseAddOrEditContract.View> implements BaseAddOrEditContract.Presenter<ModelType>{

    public BaseAddOrEditPresenter(ViewType mView, Context mContext, Bundle savedInstanceState) {
        super(mView, mContext, savedInstanceState);
    }

    protected void returnResultToActivity(){
        mView.returnResultToActivity();
    }

    @Override
    protected void initialize() {

    }
}
