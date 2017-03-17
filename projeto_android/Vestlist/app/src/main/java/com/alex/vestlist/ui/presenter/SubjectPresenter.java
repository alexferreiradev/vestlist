package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.model.Subject;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class SubjectPresenter extends BaseListInfoPresenter<Subject> {

    public SubjectPresenter(BaseListInfoPresenter.View mView, Context context) {
        super(mView, context);
    }

    @Override
    public void initialize() {
        mView.setEmptyView();
        mView.toggleProgressBar();
        mView.loadList();
    }

    @Override
    public void onLoadedList(List<Subject> result) {
        mView.toggleProgressBar();
        if (result == null || result.isEmpty()){
            mView.toggleEmptyView();
        }else {
            mView.showList(result);
        }
    }

    @Override
    public void onUpdateListActionSelected() {
    }

    @Override
    public void onAddActionSelected() {
    }

    /**
     * Funções que somente este presenter usa da View
     */
    public interface View extends ListInfoViewInterface<Subject>{
        // Nenhum função
    }
}
