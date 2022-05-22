package com.supercharger.app.dao;

import java.util.List;

public interface IGenericDao<T> {

    List<T> findAll();

    T findOne(final Long id);

    T save(final T entity);

    T update(final T entity);

    public void delete(final T entity);

}