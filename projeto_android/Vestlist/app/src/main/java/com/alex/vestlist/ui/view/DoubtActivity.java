package com.alex.vestlist.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.ui.adapter.DoubtListViewAdapter;
import com.alex.vestlist.ui.presenter.DoubtPresenter;

import java.util.List;

public class DoubtActivity extends BaseListViewActivity<Doubt, DoubtPresenter, DoubtPresenter.View> implements DoubtPresenter.View {

    public static final String ARGUMENT_LIST_KEY = "list argument";
    private DoubtListViewAdapter mAdapter;
    private ExerciseList mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt);
        mPresenter = new DoubtPresenter(this, this, savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doubt_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newDoubtAction:
                Doubt doubt = new Doubt();
                doubt.setListId(mList.getId());
                mPresenter.showAddOrEditView(doubt);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            mPresenter.reCreateAdapter();

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        mList = (ExerciseList) getIntent().getExtras().getSerializable(ARGUMENT_LIST_KEY);
        super.initializeWidgets(savedInstanceState);
    }

    @Override
    public void createListAdapter(List results) {
        mAdapter = new DoubtListViewAdapter(this, results, R.layout.adapter_subject_listview);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void addAdapterData(List<Doubt> result) {
        mAdapter.addAll(result);
    }

    @Override
    public void removeAdapterData(List<Doubt> result) {
//        mAdapter. TODO
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
    public void showAddOrEditDataView(Doubt data) {

    }

    @Override
    public void showDataView(Doubt data) {

    }

    @Override
    public ExerciseList getExerciseList() {
        return mList;
    }
}
