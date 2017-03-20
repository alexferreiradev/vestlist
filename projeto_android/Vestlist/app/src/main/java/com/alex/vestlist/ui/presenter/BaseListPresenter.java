package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

import static com.alex.vestlist.ui.presenter.BasePresenter.TaskType.FILTER_FROM_ADAPTER;
import static com.alex.vestlist.ui.presenter.BasePresenter.TaskType.FILTER_FROM_SOURCE;
import static com.alex.vestlist.ui.presenter.BasePresenter.TaskType.LOAD;

/**
 * Created by Alex on 17/03/2017.
 */

/**
 * Classe base para criar presenter para activities que tenham listview ou algum container de info.
 * @param <ViewType> - Interface
 * @param <ModelType> - Tipo de model
 */
public abstract class BaseListPresenter<ViewType extends BaseListContract.View , ModelType extends BaseModel>
        extends BasePresenter<ViewType, ModelType>
        implements BaseListContract.Presenter<ModelType>{

    private static final int LIMIT_INITIAL = 50; // 30 pronto para mostrar + 20 esperando no adapter
    public static final int INTERVAL_TO_LOAD_MORE = 20; // 30 já visto, 20 faltando para visualizar
    private static final int TOTAL_FILTER_FROM_ADAPTER = 200;

    private int mLoadItemsLimit;
    protected int mOffset;
    private String filterKey;
    private String filterValue;

    protected BaseListPresenter(ViewType mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    protected abstract void setEmptyView();

    protected abstract List<ModelType> loadDataFromSource(int offset, int loadItemsLimit);

    protected abstract int updateDataFromSource(ModelType data);

    protected abstract boolean removeDataFromSource(ModelType data);

    protected abstract Long saveDataFromSource(ModelType data);

    protected abstract List<ModelType> applyFilterFromAdapter();

    protected abstract List<ModelType> applyFilterFromSource();


    protected abstract void showDataNotEditedError();

    protected abstract void showDataEditedSuccess();

    protected abstract void showDataSavedSuccess();

    protected abstract void showDataRemovedError();

    protected abstract void showDataRemovedSuccess();

    protected abstract void showDataNotSavedError();

    protected void showDataFilteredSuccess() {
        mView.showSuccessMsg("Filtro aplicado com sucesso");
    }

    @Override
    public void analiseBackgroundThreadResultData(Object result, TaskType taskType) {
        switch (taskType){
            case LOAD:
                List list = (List) result;
                if (list == null || list.isEmpty()) {
                    if (isNewAdapter()) {
                        mView.destroyListAdapter();
                    }
                    return;
                }
                if (isNewAdapter())
                    mView.createListAdapter(list);
                else
                    mView.addAdapterData(list);
                break;
            case SAVE:
                Long dataId = (Long) result;
                if (dataId <= 0){
                    showDataNotSavedError();
                }else {
                    showDataSavedSuccess();
                    reCreateAdapter();
                }
                break;
            case REMOVE:
                boolean deleted = (boolean) result;
                if (!deleted){
                    showDataRemovedError();
                }else {
                    showDataRemovedSuccess();
                    reCreateAdapter();
                }
                break;
            case EDIT:
                int rowsUpdated = (int) result;
                if (rowsUpdated <= 0){
                    showDataNotEditedError();
                }else {
                    showDataEditedSuccess();
                    reCreateAdapter();
                }
                break;
            case FILTER_FROM_ADAPTER:
            case FILTER_FROM_SOURCE:
                list = (List) result;
                if (list == null || list.isEmpty())
                    return;
                else{
                    showDataFilteredSuccess();
                    mView.createListAdapter(list);
                }
                break;

        }
    }

    @Override
    public void applyFilter(String filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
        BaseAdapter adapter = mView.getAdapter();
        if (adapter != null && adapter.getCount() <= TOTAL_FILTER_FROM_ADAPTER){
            startBackgroundThread(null, FILTER_FROM_ADAPTER);
        }else
            startBackgroundThread(null, FILTER_FROM_SOURCE);
    }

    public void populateAdapter(List<ModelType> result){
        if (result != null && !result.isEmpty() && mOffset <= 0){
            mView.createListAdapter(result);
        }else if (result != null){
            mView.addAdapterData(result);
        }
    }

    /**
     * Verifica se precisa de carregar mais itens ao usuário fazer scroll na lista de itens. São feitas
     * duas verificacoes: 1- se precisa de carregar mais (ultimo visivel perto de total carregado);
     * 2 - se não foi feito uma requisicao de carregamento antes (offset > total carregado);
     *
     * Deve alterar o limit de acordo com o total de linhas visiveis e o offset com adição do limit.
     *
     * @param firstVisibleItem - posição do primeiro item visível
     * @param visibleItemCount - total de linhas que são visíveis
     * @param adapterTotalItems - total de itens no adapter
     */
    public synchronized void loadMoreData(int firstVisibleItem, int visibleItemCount, int adapterTotalItems){
        int lastItemVisiblePosition = firstVisibleItem + visibleItemCount;
        if (lastItemVisiblePosition > adapterTotalItems - INTERVAL_TO_LOAD_MORE){
            if (mOffset <= adapterTotalItems){
                startBackgroundThread(null, LOAD);
            }
        }

        if (mLoadItemsLimit < visibleItemCount + INTERVAL_TO_LOAD_MORE)
            mLoadItemsLimit = visibleItemCount + INTERVAL_TO_LOAD_MORE;
    }



    @Override
    public synchronized Object taskFromSource(ModelType data, TaskType taskType) {
        switch (taskType){
            case EDIT:
                return updateDataFromSource(data);
            case SAVE:
                return saveDataFromSource(data);
            case REMOVE:
                return removeDataFromSource(data);
            case LOAD:
                List<ModelType> modelTypes = loadDataFromSource(mOffset, mLoadItemsLimit);
                mOffset = mOffset + mLoadItemsLimit;
                return modelTypes;
            case FILTER_FROM_SOURCE:
                return applyFilterFromSource();
            case FILTER_FROM_ADAPTER:
                return applyFilterFromAdapter();
        }
        return null;
    }



    public void reCreateAdapter(){
        mView.destroyListAdapter();
        initialize();
    }

    @Override
    protected void initialize() {
        mView.destroyListAdapter();
        resetPaginationCounter();
        loadMoreData(0, 0, 0);
    }

    @Override
    protected void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);
        setEmptyView();
    }

    protected boolean isNewAdapter() {
        return mView.getAdapter() == null || mView.getAdapter().isEmpty() ? true : false;
    }

    private void resetPaginationCounter(){
        mLoadItemsLimit = LIMIT_INITIAL;
        mOffset = 0;
    }
}
