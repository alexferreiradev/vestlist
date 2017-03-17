package com.alex.vestlist.ui.presenter;

import android.content.Context;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public abstract class BaseListInfoPresenter<T> extends BasePresenter<BaseListInfoPresenter.View> {

    public BaseListInfoPresenter(View mView, Context context) {
        super(mView, context);
    }

    public void onLoadedList(List<T> result){
        mView.toggleProgressBar();
        if (result == null || result.isEmpty())
            mView.toggleEmptyView();
        else
            mView.showList(result);
    }

    public abstract void onUpdateListActionSelected();

    public abstract void onAddActionSelected();

    @Override
    public void initialize() {
        mView.setEmptyView();
        mView.toggleEmptyView();
        mView.toggleProgressBar();
        mView.loadList();
    }

    /**
     * Funções que somente este presenter usa da View
     */
    public interface View<T> extends ListInfoViewInterface<T>{

    }
}
