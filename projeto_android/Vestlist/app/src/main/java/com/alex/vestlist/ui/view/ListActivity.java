package com.alex.vestlist.ui.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.alex.vestlist.R;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.adapter.ExerciseListListViewAdapter;
import com.alex.vestlist.ui.presenter.ListPresenter;

import java.util.List;

public class ListActivity extends BaseListViewActivity<ExerciseList, ListPresenter, ListPresenter.View> implements ListPresenter.View{

    public static final String ARGUMENT_TEACHER_KEY = "teacher extra" ;
    private Teacher mTeacher;
    private ExerciseListListViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mPresenter = new ListPresenter(this, this, savedInstanceState);
    }

    @Override
    public void initializeArgumentsFromIntent() {
        mTeacher = (Teacher) getIntent().getExtras().getSerializable(ARGUMENT_TEACHER_KEY);

        if (mTeacher == null || mTeacher.getId() <= 0)
            throw new IllegalArgumentException("professor nulo ou sem id");

        getSupportActionBar().setTitle(mTeacher.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newList:
                mPresenter.showAddOrEditView(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void createListAdapter(List results) {
        mAdapter = new ExerciseListListViewAdapter(this, results, R.layout.adapter_exerciselist_listview, mPresenter);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void addAdapterData(List<ExerciseList> result) {
        mAdapter.addAll(result);
    }

    @Override
    public void removeAdapterData(List<ExerciseList> result) {
        mAdapter.removeAll(result);
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
    public void showAddOrEditDataView(final ExerciseList data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String name = "";
        if (data != null && !data.getName().isEmpty())
            name = data.getName();

        final EditText etvName = new EditText(this);
        etvName.setText(name);

        builder.setView(etvName);
        builder.setTitle("Digite o nome da lista:");
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textTyped = etvName.getText().toString();
                ExerciseList exerciseList = new ExerciseList();
                exerciseList.setName(textTyped);
                exerciseList.setTeacherId(mTeacher.getId());
                mPresenter.startSaveOrEditDataInSource(exerciseList);
            }
        });
        builder.create().show();
    }

    @Override
    public void showDataView(ExerciseList data) {

    }

    @Override
    public Teacher getTeacher() {
        return mTeacher;
    }

}
