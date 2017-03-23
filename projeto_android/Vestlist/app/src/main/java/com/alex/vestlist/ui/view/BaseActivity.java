package com.alex.vestlist.ui.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alex.vestlist.R;
import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.ui.presenter.BasePresenter;

/**
 * Created by Alex on 16/03/2017.
 */

public abstract class BaseActivity<ModelType extends BaseModel,
        ViewType extends BasePresenter.View,
        PresenterType extends BasePresenter>
        extends AppCompatActivity
        implements BasePresenter.View<ModelType>{

    protected BasePresenter.TaskType mTaskType;
    protected ModelType mData;
    protected PresenterType mPresenter;
    protected ProgressBar mProgressBar;


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.startPresenterView();
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (mProgressBar == null)
            throw new NullPointerException("A Activity não tem progressBar no layout.");
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toggleProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE)
            mProgressBar.setVisibility(View.GONE);
        else
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void startBackgroundThread(ModelType data, BasePresenter.TaskType taskType) {
        mTaskType = taskType;
        mData = data;
        new BackgroundTask().execute();
    }

    private final class BackgroundTask extends AsyncTask<String, Integer, Object>{
        @Override
        protected Object doInBackground(String... params) {
            if (mPresenter == null)
                return null;

            return mPresenter.taskFromSource(mData, mTaskType);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            if (mPresenter != null){
                mPresenter.analiseBackgroundThreadResult(result, mTaskType);
            }
        }
    }
}
