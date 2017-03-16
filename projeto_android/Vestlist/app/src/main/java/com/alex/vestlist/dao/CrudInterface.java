package com.alex.vestlist.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public interface CrudInterface<T> {

    public T get(long id);

    public long save(T object);

    /**
     * Apaga o objeto com id = id
     * @param id
     * @return - número de linhas alterado. Se == 1, ok.
     */
    public int delete(long id);

    public List<T> search(String key, String value, int offset, int limit);

    /**
     * Carrega um lista de objetos, com a restrição de quantidade por carregamento. Serve para paginação.
     * @param offset - total já carregado
     * @param limit - total a ser carregado
     * @return - lista com total de itens = limit.
     */
    public List<T> load(int offset, int limit);

    public int update(long id, ContentValues values);
}
