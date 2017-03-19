package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;

import com.alex.vestlist.model.BaseModel;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

/**
 * Classe base para criar presenter para activities que tenham listview ou algum container de info.
 * @param <ViewType> - Interface
 * @param <Model> - Tipo de model
 */
public abstract class BaseListPresenter<ViewType extends BaseListContract.View , Model extends BaseModel>
        extends BasePresenter<ViewType>
        implements BaseListContract.Presenter<Model>{

    public BaseListPresenter(ViewType mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
        initialize();
    }

    public void populateList(List<Model> result, int offset){
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
     * @param offset - total de itens pedidos para carregar
     * @param loadItemsLimit - limite para carregar itens
     * @param firstVisibleItem - posição do primeiro item visível
     * @param visibleItemCount - total de linhas que são visíveis
     * @param adapterTotalItens - total de itens no adapter
     */
    public void loadMoreData(int offset, int loadItemsLimit, int firstVisibleItem, int visibleItemCount, int adapterTotalItens){
        int lastItemVisiblePosition = firstVisibleItem + visibleItemCount;
        if (lastItemVisiblePosition > adapterTotalItens - 20){
            if (offset <= adapterTotalItens){
                mView.toggleProgressBar();
                mView.startLoadThread(offset);
                mView.setOffsetValue(offset + loadItemsLimit);
            }
        }

        if (loadItemsLimit < visibleItemCount + 20)
            mView.setLoadItemsLimitValue(visibleItemCount + 20);
    }

    /**
     * Fazer busca de dados em algum local
     * @param offset - total de itens carregados
     * @param loadItemsLimit - limit a ser carregado
     * @return - lista de itens
     *
     */
    public abstract List<Model> loadDataFromSource(int offset, int loadItemsLimit);

    /**
     * Recarrega os dados para lista, desde o primeiro. Caso os dados mudaram, seram atualizados no adapter.
     */
    public void reloadList(){
        initialize();
    }


    @Override
    protected void initialize() {
        mView.resetPagingCounters();
        loadMoreData(0, 0, 0, 0, 0);
    }

    @Override
    protected void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);
        setEmptyView();
    }

    protected abstract void setEmptyView();

    @Override
    public void showfilterListView() {
        // show view to filter
    }

    @Override
    public void startAddOrEditThread(Model data) {
        mView.startSaveOrEditThread(data);
    }

    protected abstract void showSuccessMsg();

    @Override
    public void analiseSaveThreadResult(Long id) {
        if (id > 0){
            showSuccessMsg();// TODO fazer msg para cada operação com metodo abstract para cada uma
            reloadList();
        }else{
            showErrorMsg();// TODO fazer msg para cada operação com metodo abstract para cada uma
            reloadList();
        }
    }

    protected abstract void showErrorMsg();
}
