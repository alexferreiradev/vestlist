package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alex.vestlist.R;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.adapter.ExerciseListListViewAdapter;
import com.alex.vestlist.ui.presenter.ListPresenter;

import java.util.List;

public class ListActivity extends BaseListViewActivity<ExerciseList, ListPresenter, ListPresenter.View> implements ListPresenter.View {

    public static final String TEACHER_EXTRA_KEY = "teacher extra" ;
    private Teacher mTeacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mPresenter = new ListPresenter(this, this, savedInstanceState);
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);

        mTeacher = (Teacher) getIntent().getExtras().getSerializable(TEACHER_EXTRA_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onUpdateListActionSelected();
    }

    @Override
    public void setListAdapter(List results) {
        mListView.setAdapter(new ExerciseListListViewAdapter(this, results, R.layout.activity_list));
    }

    @Override
    public void setEmptyView() {
        mEmptyView.setText("Não listas");
    }

    @Override
    public void addAdapterItems(List result) {

    }

    @Override
    public void showNewListDialog() {
        // Nova lista
    }

    @Override
    public Teacher getTeacher() {
        return mTeacher;
    }
}
