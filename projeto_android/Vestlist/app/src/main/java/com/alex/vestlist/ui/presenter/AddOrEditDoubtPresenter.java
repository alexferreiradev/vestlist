package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by Alex on 19/03/2017.
 */

public class AddOrEditDoubtPresenter extends BasePresenter<AddOrEditDoubtPresenter.View> {

    public AddOrEditDoubtPresenter(View mView, Context mContext, Bundle savedInstanceState) {
        super(mView, mContext, savedInstanceState);
    }

    @Override
    protected void initialize() {
        // TODO start teclado no primeiro ETV
    }

    public interface View extends BasePresenter.View{

    }
}
