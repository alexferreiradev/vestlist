package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.widget.BaseAdapter;

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
    public void createListAdapter(List results) {
        mAdapter = new SubjectListViewAdapter(this, results, R.layout.adapter_subject_listview);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void removeAdapterData(List<Subject> result) {

    }

    @Override
    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(BaseAdapter newAdapter) {
        mAdapter = (SubjectListViewAdapter) newAdapter;
    }

    @Override
    public void showAddOrEditDataView(Subject data) {

    }

    @Override
    public void showDataView(Subject data) {

    }

    @Override
    public void addAdapterData(List result) {
        if (mAdapter != null)
            mAdapter.addAll(result);
    }

}
