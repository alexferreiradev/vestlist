package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.bussiness.StudentBusiness;

/**
 * Created by Alex on 16/03/2017.
 */

public class Presenter <T extends Presenter.View> {

    protected Context context;
    protected T mView;
    protected StudentBusiness mBussiness;

    public Presenter(T mView, Context context) {
        this.context = context;
        this.mView = mView;
        this.mBussiness = new StudentBusiness(context);
    }

    public void initialize(){

    }

    public void update(){

    }

    /**
     * Funções que todas mView tem
     */
    public interface View {

    }
}
