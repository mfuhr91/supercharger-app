package com.supercharger.app.services;

import java.util.List;

public interface IGenericService<T> {

    List<T> findAll();

    T findOne(final Long id);

    void save(final T entity);

    void update(final T entity);

    public void delete(final T entity);

}
