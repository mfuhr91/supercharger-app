package com.supercharger.app.dao;

import java.util.List;

public interface IGenericDao<T> {

    public List<T> findAll();

    public T findOne(final Long id);

    public void save(final T entity);

    public void update(final T entity);

    public void delete(final T entity);

}