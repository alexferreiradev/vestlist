package com.alex.vestlist.ui.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

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
    public void toggleProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE)
            mProgressBar.setVisibility(View.GONE);
        else
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void startBackgroundThread(ModelType data, BasePresenter.TaskType taskType) {
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
