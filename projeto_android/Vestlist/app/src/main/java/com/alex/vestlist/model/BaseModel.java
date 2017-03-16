package com.alex.vestlist.model;

import java.io.Serializable;

/**
 * Created by Alex on 16/03/2017.
 */

public abstract class BaseModel<T> implements ModelSqlInterface<T>, Serializable {

    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
