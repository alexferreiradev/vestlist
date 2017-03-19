package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.source.StudentSource;

/**
 * Created by Alex on 16/03/2017.
 */

public abstract class BasePresenter<ViewType extends BasePresenter.View,
        ModelType extends BaseModel> {

    protected Context mContext;
    protected ViewType mView;
    protected StudentSource mSource;

    protected BasePresenter(ViewType mView, Context mContext, Bundle savedInstanceState) {
        this.mContext = mContext;
        this.mView = mView;
        this.mSource = new StudentSource(mContext);
        initializeWidgets(savedInstanceState);
    }

    /**
     * Deve ser chamado depois de chamar initializeWidgets.
     *
     * Deve fazer mostrar views.
     */
    protected abstract void initialize();

    public abstract Object taskFromSource(ModelType data, TaskType taskType);

    public abstract void analiseBackgroundThreadResult(Object result, TaskType taskType);

    public void startSaveOrEditDataInSource(ModelType data) {
        if (data == null)
            throw new NullPointerException("Dado a ser salvo estava nulo");
        if (data.getId() > 0)
            startBackgroundThread(data, TaskType.EDIT);
        else
            startBackgroundThread(data, TaskType.SAVE);
    }

    /**
     * Chamado para fazer bind entre view e atributos da activity.
     * @param savedInstanceState -
     */
    protected void initializeWidgets(Bundle savedInstanceState) {
        mView.initializeWidgets(savedInstanceState);
        initialize();
    }

    protected void startBackgroundThread(ModelType data, TaskType taskType){
        mView.toggleProgressBar();
        mView.startBackgroundThread(data, taskType);
    }

    /**
     * Funções que todas mView tem
     */
    public interface View<ModelType extends BaseModel> {

        /**
         * Inverte o atributo visible de um progressBar
         */
        public void toggleProgressBar();

        /**
         * Faz bind entre view e atributos da activity
         * @param savedInstanceState - dados da instancia salvos
         */
        public void initializeWidgets(Bundle savedInstanceState);

        public void showErrorMsg(String msg);

        public void showSuccessMsg(String msg);

        public void startBackgroundThread(ModelType data, TaskType taskType);

    }

    public enum TaskType{
        SAVE,
        EDIT,
        REMOVE,
        LOAD,
        FILTER_FROM_ADAPTER,
        FILTER_FROM_SOURCE,
    }
}
