package com.alex.vestlist.ui.presenter;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public interface ListInfoViewInterface<T> extends BasePresenter.View {

    public void showList(List<T> results);

    public void loadList();

    public void setEmptyView();

    public void toggleEmptyView();
}
