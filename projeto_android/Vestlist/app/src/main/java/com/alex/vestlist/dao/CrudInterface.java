package com.alex.vestlist.dao;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public interface CrudInterface<T> {

    public T get(long id);

    public long save(T object);

    public int delete(long id);

    public List<T> search(String key, String value, String limit);
}
