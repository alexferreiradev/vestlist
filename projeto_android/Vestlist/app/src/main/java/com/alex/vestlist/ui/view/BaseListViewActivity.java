package com.alex.vestlist.ui.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.ui.presenter.BaseListContract;
import com.alex.vestlist.ui.presenter.BaseListPresenter;

import java.util.List;

import static com.alex.vestlist.R.id.emptyView;
import static com.alex.vestlist.R.id.listView;
import static com.alex.vestlist.R.id.progressBar;

/**
 * Created by Alex on 17/03/2017.
 */

abstract class BaseListViewActivity<ModelType extends BaseModel,
        Presenter extends BaseListPresenter,
        ViewType extends BaseListContract.View>
        extends BaseActivity
        implements AbsListView.OnScrollListener, BaseListContract.View<ModelType> {

    protected int mLoadItemsLimit;
    protected int mOffset;
    protected Presenter mPresenter;
    protected ListView mListView;
    protected TextView mEmptyView;
    protected ProgressBar mProgressBar;

    /**
     * valor inicial para total a ser carregado
     */
    protected static final int LIMIT_INITIAL = 20;

    @Override
    public void toggleProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE)
            mProgressBar.setVisibility(View.GONE);
        else
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void toggleEmptyView() {
        if (mEmptyView.getVisibility() == View.VISIBLE)
            mEmptyView.setVisibility(View.GONE);
        else
            mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyView(String text) {
        mEmptyView.setText(text);
    }

    @Override
    public void resetPagingCounters() {
        mLoadItemsLimit = LIMIT_INITIAL;
        mOffset = 0;
        mListView.setAdapter(null);
    }

    @Override
    public void setOffsetValue(int newValue) {
        mOffset = newValue;
    }

    @Override
    public void setLoadItemsLimitValue(int newLimit) {
        mLoadItemsLimit = newLimit;
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showSuccessMsg(String msg) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null)
            mPresenter.reloadList();
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState){
        mListView = (ListView) findViewById(listView);
        mEmptyView = (TextView) findViewById(emptyView);
        mListView.setEmptyView(mEmptyView);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);
        mProgressBar = (ProgressBar) findViewById(progressBar);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mPresenter != null) {
            ModelType item = (ModelType) parent.getAdapter().getItem(position);
            mPresenter.openDataDetails(item);
        }
    }

    /**
     *
     * @param view
     * @param firstVisibleItem - posicao do item da primeira celula
     * @param visibleItemCount - total de células visiveis
     * @param totalItemCount - total no adapter
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mPresenter != null && mOffset > 0)
            mPresenter.loadMoreData(mOffset, mLoadItemsLimit, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void startLoadThread(int offset) {
        String[] params = {String.valueOf(offset)};
        new LoadSubjects().execute(params);
    }

    public class LoadSubjects extends AsyncTask<String, Integer, List> {

        @Override
        protected List doInBackground(String... params) {
            String offsetString = params[0];
            if (mPresenter == null)
                return null;

            return mPresenter.loadDataFromSource(Integer.parseInt(offsetString), mLoadItemsLimit);
        }

        @Override
        protected void onPostExecute(List list) {
            List listBase = list;
            if (mPresenter != null)
                mPresenter.populateList(listBase, mOffset);
        }
    }
}
