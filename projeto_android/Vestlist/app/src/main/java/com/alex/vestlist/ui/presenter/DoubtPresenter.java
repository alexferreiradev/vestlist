package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class DoubtPresenter extends BaseListPresenter<DoubtPresenter.View, Doubt> {

    public DoubtPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    public List<Doubt> loadDataFromSource(int offset, int loadItemsLimit) {
        return null;
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há dúvidas");
    }

    @Override
    protected void showSuccessMsg() {

    }

    @Override
    protected void showErrorMsg() {

    }

    @Override
    public void populateFilteredList(String filterType) {

    }

    @Override
    public void showAddOrEditView(Doubt data) {

    }

    @Override
    public int updateModelInSource(Doubt data) {
        return 0;
    }

    @Override
    public void openDataDetails(Doubt item) {

    }

    public interface View extends BaseListContract.View<Doubt>{

    }
}
