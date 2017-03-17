package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.bussiness.StudentBusiness;

/**
 * Created by Alex on 16/03/2017.
 */

public abstract class BasePresenter<ViewType extends BasePresenter.View> {

    protected Context context;
    protected ViewType mView;
    protected StudentBusiness mBussiness;

    public BasePresenter(ViewType mView, Context context, Bundle savedInstanceState) {
        this.context = context;
        this.mView = mView;
        this.mBussiness = new StudentBusiness(context);
        initializeWidgets(savedInstanceState);
    }

    /**
     * Deve ser chamado depois de chamar initializeWidgets.
     *
     * Deve fazer mostrar views.
     */
    protected abstract void initialize();

    /**
     * Chamado para fazer bind entre view e atributos da activity.
     * @param savedInstanceState -
     */
    public void initializeWidgets(Bundle savedInstanceState) {
        mView.initializeWidgets(savedInstanceState);
    }

    /**
     * Funções que todas mView tem
     */
    public interface View {

        /**
         * Inverte o atributo visible de um progressBar
         */
        public void toggleProgressBar();

        /**
         * Faz bind entre view e atributos da activity
         * @param savedInstanceState - dados da instancia salvos
         */
        public void initializeWidgets(Bundle savedInstanceState);
    }
}
