package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Seguro;

import java.util.List;

public class SegurosDao implements IGenericDao<Seguro> {

    DBConnection db;

    public SegurosDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List findAll() {
        String statement = "FROM Seguro";
        return (List<Seguro>) db.getManager().createQuery(statement).getResultList();

    }

    @Override
    public Seguro findOne(Long id) {


        String statement = "FROM Seguro WHERE id=" + id;

        return (Seguro) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Seguro entity) {

    }

    @Override
    public void update(Seguro entity) {

    }

    @Override
    public void delete(Seguro entity) {
    }
}
