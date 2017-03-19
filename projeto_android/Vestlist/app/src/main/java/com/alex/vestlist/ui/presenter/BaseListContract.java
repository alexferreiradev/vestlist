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

        /**
         * Reinicia os contadores de paginação da lista de info.
         * Usado para dar update na lista ou recarregar os elementos desde do 0.
         *
         * Use a constante LIMIT_INITITAL.
         */
        public void resetPagingCounters();

        public void showAddOrEditDataView();

        public void setOffsetValue(int newValue);

        void setLoadItemsLimitValue(int newLimit);

        void addAdapterData(List<ModelType> result);

        void startSaveOrEditThread(ModelType data);
    }

    public interface Presenter<ModelType> {


        public void populateList(List<ModelType> result, int offset);

        public void loadMoreData(
                int offset, int loadItemsLimit, int firstVisibleItem,
                int visibleItemCount, int adapterTotalItens);

        public abstract List<ModelType> loadDataFromSource(int offset, int loadItemsLimit);


        public void reloadList();

        public void showfilterListView();

        public void populateFilteredList(String filterType);

        public abstract void openDataDetails(ModelType item);


        public abstract void showAddOrEditView(ModelType data);

        public abstract int updateModelInSource(ModelType data);

        public abstract void startAddOrEditThread(ModelType data);

        public abstract void analiseSaveThreadResult(Long rowsUpdated);

    }
}
