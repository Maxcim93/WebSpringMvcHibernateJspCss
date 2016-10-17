package com.maxim.web.storages;

import java.util.Collection;

/**
 * Created by Максим on 29.08.2016.
 */
public interface Storage<T> {

    public Collection<T> values();

    public int add(final T object);

    public void edit(final T object);

    public void delete(final int id);

    public T getById(final int id);

    public T getByLogin(String login);

    public void close();
}
