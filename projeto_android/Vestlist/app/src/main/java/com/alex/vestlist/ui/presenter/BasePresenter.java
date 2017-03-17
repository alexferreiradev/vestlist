package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.bussiness.StudentBusiness;

/**
 * Created by Alex on 16/03/2017.
 */

public abstract class BasePresenter<T extends BasePresenter.View> {

    protected Context context;
    protected T mView;
    protected StudentBusiness mBussiness;

    public BasePresenter(T mView, Context context) {
        this.context = context;
        this.mView = mView;
        this.mBussiness = new StudentBusiness(context);
    }

    public abstract void initialize();

    public void update(){

    }

    /**
     * Funções que todas mView tem
     */
    public interface View {
        public void toggleProgressBar();
    }
}
