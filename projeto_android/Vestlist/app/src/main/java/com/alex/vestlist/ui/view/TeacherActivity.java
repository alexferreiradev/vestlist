package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.adapter.TeacherListViewAdapter;
import com.alex.vestlist.ui.presenter.TeacherPresenter;

import java.util.List;

public class TeacherActivity extends BaseListViewActivity<Teacher, TeacherPresenter, TeacherPresenter.View> implements TeacherPresenter.View {
    public static final String ARGUMENT_SUBJECT_KEY = "subject extra";
    private TeacherListViewAdapter mAdapter;
    private Subject mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mPresenter = new TeacherPresenter(this, this, savedInstanceState);
    }

    @Override
    public void initializeArgumentsFromIntent() {
        mSubject = (Subject) getIntent().getExtras().getSerializable(ARGUMENT_SUBJECT_KEY);

        if (mSubject == null || mSubject.getId() <= 0) {
            throw new IllegalArgumentException("materia nula ou sem id");
        }

        getSupportActionBar().setTitle(mSubject.getName());
    }

    @Override
    public Subject getSubject() {
        return mSubject;
    }

    @Override
    public void createListAdapter(List results) {
        mAdapter = new TeacherListViewAdapter(this, results, R.layout.adapter_subject_listview);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void removeAdapterData(List<Teacher> result) {

    }

    @Override
    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void destroyListAdapter() {
        mAdapter = null;
        mListView.setAdapter(null);
    }

    @Override
    public void showAddOrEditDataView(Teacher data) {

    }

    @Override
    public void showDataView(Teacher data) {

    }

    @Override
    public void addAdapterData(List result) {
        mAdapter.addAll(result);
    }

}
