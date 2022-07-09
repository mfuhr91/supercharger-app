package com.supercharger.app.services;

import java.util.List;

public interface IGenericService<T> {

    public List<T> findAll();

    public T findOne(final Long id);

    public void save(final T entity);

    public void update(final T entity);

    public void delete(final T entity);

}
