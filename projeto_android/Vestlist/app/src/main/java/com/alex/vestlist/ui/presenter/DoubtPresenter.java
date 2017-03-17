package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.Doubt;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class DoubtPresenter extends BaseListInfoPresenter<DoubtPresenter.View, Doubt> {

    public DoubtPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    public List<Doubt> requestLoadInfo(int offset, int loadItemsLimit) {
        return null;
    }

    @Override
    public void onAddActionSelected() {

    }

    @Override
    public void onItemSelected(Context context, Doubt item) {

    }

    @Override
    public void onAddedListViewItem() {

    }

    public interface View extends BaseListInfoPresenter.View{

    }
}
