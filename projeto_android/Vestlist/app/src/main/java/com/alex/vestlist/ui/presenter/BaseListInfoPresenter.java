package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.AbsListView;

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
public abstract class BaseListInfoPresenter<ViewType extends BaseListInfoPresenter.View, Model extends BaseModel> extends BasePresenter<ViewType> {

    public BaseListInfoPresenter(ViewType mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
        initialize();
    }

    public void onLoadedList(List<Model> result, int offset){
        mView.toggleProgressBar();
        if (result == null || result.isEmpty()) {
            mView.toggleEmptyView();
        }
        else if (offset <= 0){
                mView.setListAdapter(result);
        }else {
            mView.addAdapterItems(result);
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
    public void loadMoreItems(int offset, int loadItemsLimit, int firstVisibleItem, int visibleItemCount, int adapterTotalItens){
        int lastItemVisiblePosition = firstVisibleItem + visibleItemCount;
        if (lastItemVisiblePosition > adapterTotalItens - 20){
            if (offset <= adapterTotalItens){
                mView.toggleProgressBar();
                mView.loadList(offset);
                mView.setOffset(offset + loadItemsLimit);
            }
        }

        if (loadItemsLimit < visibleItemCount + 20)
            mView.setLoadItemsLimit(visibleItemCount + 20);
    }

    /**
     * Fazer busca de dados em algum local
     * @param offset - total de itens carregados
     * @param loadItemsLimit - limit a ser carregado
     * @return - lista de itens
     *
     */
    public abstract List<Model> requestLoadInfo(int offset, int loadItemsLimit);

    /**
     * Recarrega os dados para lista, desde o primeiro. Caso os dados mudaram, seram atualizados no adapter.
     */
    public void onUpdateListActionSelected(){
        mView.resetLoadedPageCounters();
    }

    public abstract void onAddActionSelected();

    public abstract void onItemSelected(Context context, Model item);

    @Override
    protected void initialize() {
        mView.resetLoadedPageCounters();
        loadMoreItems(0, 0, 0, 0, 0);
    }

    /**
     * Adiciona item no adapter e mostra listView
     */
    public abstract void onAddedListViewItem();




    /**
     * Funções que somente este presenter usa da View
     */
    public interface View extends BasePresenter.View, AbsListView.OnScrollListener, AbsListView.OnItemClickListener{

        public void setListAdapter(List results);

        /**
         * Carrega dados para lista de info
         * @param offset - total já carregado e mostrado para o usuario.
         */
        public void loadList(int offset);

        public void setEmptyView();

        public void toggleEmptyView();

        /**
         * Reinicia os contadores de paginação da lista de info.
         * Usado para dar update na lista ou recarregar os elementos desde do 0.
         *
         * Use a constante LIMIT_INITITAL.
         */
        public void resetLoadedPageCounters();

        /**
         * Chama view que requisita dados do usuario.
         *
         */
        public void addListViewItem();

        /**
         * Seta o offset de paginação.         *
         */
        public void setOffset(int newValue);

        /**
         * Seta o limit do total de items a serem carregados por paginação.
         * @param newLimit
         */
        void setLoadItemsLimit(int newLimit);

        /**
         * Adiciona items em um adapter já existe.
         * @param result
         */
        void addAdapterItems(List result);
    }
}
