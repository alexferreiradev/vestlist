package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alex.vestlist.model.BaseModel;
import com.alex.vestlist.ui.presenter.BaseListContract;
import com.alex.vestlist.ui.presenter.BaseListPresenter;

import static com.alex.vestlist.R.id.emptyView;
import static com.alex.vestlist.R.id.listView;

/**
 * Created by Alex on 17/03/2017.
 */

abstract class BaseListViewActivity<ModelType extends BaseModel,
        Presenter extends BaseListPresenter,
        ViewType extends BaseListContract.View>
        extends BaseActivity<ModelType, ViewType, Presenter>
        implements AbsListView.OnScrollListener, BaseListContract.View<ModelType> {

    protected ListView mListView;
    protected TextView mEmptyView;

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
    public void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);
        mListView = (ListView) findViewById(listView);
        mEmptyView = (TextView) findViewById(emptyView);
        mListView.setEmptyView(mEmptyView);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * @param view
     * @param firstVisibleItem - posicao do item da primeira celula
     * @param visibleItemCount - total de células visiveis
     * @param totalItemCount   - total no adapter
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mPresenter != null && totalItemCount > 0)
            mPresenter.loadMoreData(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mPresenter != null) {
            ModelType item = (ModelType) parent.getAdapter().getItem(position);
            mPresenter.selectItemClicked(item);
        }
    }

}