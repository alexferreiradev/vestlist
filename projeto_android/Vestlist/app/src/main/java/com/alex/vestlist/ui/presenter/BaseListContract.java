package com.alex.vestlist.ui.presenter;

import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

/**
 * Created by Alex on 18/03/2017.
 */

public interface BaseListContract {

    public interface View<ModelType extends BaseModel>
            extends BasePresenter.View, AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

        public void createListAdapter(List results);

        public void addAdapterData(List<ModelType> result);

        public void removeAdapterData(List<ModelType> result);

        public BaseAdapter getAdapter();


        public void showAddOrEditDataView(ModelType data);

        public void showDataView(ModelType data);


        public void setEmptyView(String text);

        public void toggleEmptyView();

    }

    public interface Presenter<ModelType extends BaseModel> {

        public void loadMoreData( int firstVisibleItem, int visibleItemCount, int adapterTotalItems);


        public void applyFilter(String filterKey, String filterValue);

        public abstract void selectItemClicked(ModelType item);


        public abstract void showAddOrEditView(ModelType data);

    }
}
