package com.alex.vestlist.ui.view;

import android.os.Bundle;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.ui.presenter.DoubtPresenter;

import java.util.List;

public class DoubtActivity extends BaseListViewActivity<Doubt, DoubtPresenter, DoubtPresenter.View> implements DoubtPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt);
        mPresenter = new DoubtPresenter(this, this, savedInstanceState);
    }

    @Override
    public void setListAdapter(List results) {

    }

    @Override
    public void setEmptyView() {
        mEmptyView.setText("Não há dúvidas");
    }

    @Override
    public void addAdapterItems(List result) {

    }
}
