package com.alex.vestlist.ui.view;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alex.vestlist.R;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.adapter.ExerciseListListViewAdapter;
import com.alex.vestlist.ui.presenter.ListPresenter;

import java.util.List;

public class ListActivity extends BaseListViewActivity<ExerciseList, ListPresenter, ListPresenter.View> implements ListPresenter.View {

    public static final String TEACHER_EXTRA_KEY = "teacher extra" ;
    private Teacher mTeacher;
    private ExerciseList mList;

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
        mListView.setAdapter(new ExerciseListListViewAdapter(this, results, R.layout.activity_list));
    }

    @Override
    public void showAddOrEditDataView() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText etvName = new EditText(this);
        builder.setView(etvName);
        builder.setTitle("Digite o nome da lista:");
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textTyped = etvName.getText().toString();
                ExerciseList exerciseList = new ExerciseList();
                exerciseList.setName(textTyped);
                mPresenter.startAddOrEditThread(exerciseList);
            }
        });
        builder.create().show();
    }

    @Override
    public void addAdapterData(List result) {

    }

    @Override
    public void startSaveOrEditThread(ExerciseList data) {
        mList = (ExerciseList) data;
        new SaveItem().execute();
    }

    @Override
    public Teacher getTeacher() {
        return mTeacher;
    }

    public class SaveItem extends AsyncTask<Object, Object, Long> {

        @Override
        protected Long doInBackground(Object... params) {
            return mPresenter.saveModelInSource(mList);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            mPresenter.analiseSaveThreadResult(aLong);
        }
    }
}
