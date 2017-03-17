package com.alex.vestlist.ui.view;

import android.os.Bundle;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.adapter.TeacherListViewAdapter;
import com.alex.vestlist.ui.presenter.TeacherPresenter;

import java.util.List;

public class TeacherActivity extends BaseListViewActivity<Teacher, TeacherPresenter, TeacherPresenter.View> implements TeacherPresenter.View {
    public static final String SUBJECT_EXTRA_KEY = "subject extra";
    private TeacherListViewAdapter mAdapter;
    private Subject mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mPresenter = new TeacherPresenter(this, this, savedInstanceState);
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);

        mSubject = (Subject) getIntent().getExtras().getSerializable(SUBJECT_EXTRA_KEY);
    }

    @Override
    public void setListAdapter(List results) {
        mAdapter = new TeacherListViewAdapter(this, results, R.layout.adapter_subject_listview);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void setEmptyView() {
        mEmptyView.setText("Não há professores");
    }

    @Override
    public void addAdapterItems(List result) {
        mAdapter.addAll(result);
    }

    @Override
    public Subject getSubject() {
        return mSubject;
    }
}
