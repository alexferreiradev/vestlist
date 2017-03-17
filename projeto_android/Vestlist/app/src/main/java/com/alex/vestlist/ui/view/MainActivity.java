package com.alex.vestlist.ui.view;

import android.os.Bundle;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.ui.adapter.SubjectListViewAdapter;
import com.alex.vestlist.ui.presenter.SubjectPresenter;

import java.util.List;

public class MainActivity extends BaseListViewActivity<Subject, SubjectPresenter, SubjectPresenter.View> implements SubjectPresenter.View {

    private SubjectListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new SubjectPresenter(this, this, savedInstanceState);
    }

    @Override
    public void setListAdapter(List results) {
        mAdapter = new SubjectListViewAdapter(this, results, R.layout.adapter_subject_listview);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void addAdapterItems(List result) {
        if (mAdapter != null)
            mAdapter.addAll(result);
    }

    @Override
    public void setEmptyView() {
        mEmptyView.setText("Nenhuma matéria encontrada");
    }
}
