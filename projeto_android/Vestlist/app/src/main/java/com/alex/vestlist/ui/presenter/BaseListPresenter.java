package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

import static android.R.attr.offset;
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

    private static final int LIMIT_INITIAL = 20;

    private int mLoadItemsLimit;
    private int mOffset;

    public BaseListPresenter(ViewType mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
        initialize();
    }

    public void populateAdapter(List<ModelType> result, int offset){
        mView.toggleProgressBar();
        if (result != null && !result.isEmpty() && offset <= 0){
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
    public void loadMoreData(int firstVisibleItem, int visibleItemCount, int adapterTotalItems){
        int lastItemVisiblePosition = firstVisibleItem + visibleItemCount;
        if (lastItemVisiblePosition > adapterTotalItems - 20){
            if (offset <= adapterTotalItems){
                mView.toggleProgressBar();
                mView.startBackgroundThread(null, LOAD);
                mOffset = offset + mLoadItemsLimit;
            }
        }

        if (mLoadItemsLimit < visibleItemCount + 20)
            mLoadItemsLimit = visibleItemCount + 20;
    }

    @Override
    public void startBackgroundThread(ModelType data, TaskType taskType) {
        mView.startBackgroundThread(data, taskType);
    }

    @Override
    public Object taskFromSource(ModelType data, TaskType taskType) {
        switch (taskType){
            case LOAD:
                return loadDataFromSource(mOffset, mLoadItemsLimit);
            case EDIT:
                return updateDataFromSource(data);
            case REMOVE:
                return removeDataFromSource(data);
            case SAVE:
                return saveDataFromSource(data);
        }
        return null;
    }


    /**
     * Recarrega os dados para lista, desde o primeiro. Caso os dados mudaram, seram atualizados no adapter.
     */
    public void reCreateAdapter(){
        initialize();
    }

    @Override
    public void analiseSaveThreadResult(Long id) {
        if (id > 0){
            showSuccessMsg();// TODO fazer msg para cada operação com metodo abstract para cada uma
            reCreateAdapter();
        }else{
            showErrorMsg();// TODO fazer msg para cada operação com metodo abstract para cada uma
        }
    }

    protected abstract List<ModelType> loadDataFromSource(int offset, int loadItemsLimit);

    protected abstract int updateDataFromSource(ModelType data);

    protected abstract int removeDataFromSource(ModelType data);

    protected abstract Long saveDataFromSource(ModelType data);

    protected abstract void setEmptyView();

    protected abstract void showSuccessMsg();


    protected abstract void showErrorMsg();

    private void resetPaginationCounter(){
        mLoadItemsLimit = LIMIT_INITIAL;
        mOffset = 0;
    }

    @Override
    protected void initialize() {
        resetPaginationCounter();
        loadMoreData(0, 0, 0, 0, 0);
    }

    @Override
    protected void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);
        setEmptyView();
    }
}
