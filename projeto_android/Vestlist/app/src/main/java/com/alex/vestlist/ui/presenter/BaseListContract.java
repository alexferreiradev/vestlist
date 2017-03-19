package com.alex.vestlist.ui.presenter;

import android.widget.AbsListView;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

/**
 * Created by Alex on 18/03/2017.
 */

public interface BaseListContract {

    public interface View<ModelType extends BaseModel>
            extends BasePresenter.View, AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

        public void createListAdapter(List results);

        /**
         * Carrega dados para lista de info
         * @param offset - total já carregado e mostrado para o usuario.
         */
        public void startLoadThread(int offset);

        public void setEmptyView(String text);

        public void toggleEmptyView();

        public void showAddOrEditDataView();

        public void setOffsetValue(int newValue);

        void setLoadItemsLimitValue(int newLimit);

        void addAdapterData(List<ModelType> result);

        void startSaveOrEditThread(ModelType data);
    }

    public interface Presenter<ModelType extends BaseModel> {


        public void populateAdapter(List<ModelType> result, int offset);

        public void loadMoreData(
                int offset, int loadItemsLimit, int firstVisibleItem,
                int visibleItemCount, int adapterTotalItens);


        public void reCreateAdapter();

        public abstract void applyFilterFromAdapter(String filterKey, String filterValue, List<ModelType> dataInAdapter);

        public void applyFilterFromSource(String filterKey, String filterValue);

        public abstract void selectItemClicked(ModelType item);


        public abstract void showAddOrEditView(ModelType data);

        public abstract void startAddOrEditThread(ModelType data);

        public abstract void analiseSaveThreadResult(Long rowsUpdated);

    }
}
