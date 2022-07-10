package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Constancia;

import java.util.List;

public class ConstanciaDao implements IGenericDao<Constancia> {

    DBConnection db;

    public ConstanciaDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Constancia> findAll() {
        String statement = "FROM Constancia";
        return (List<Constancia>) db.getManager().createQuery(statement).getResultList();

    }

    @Override
    public Constancia findOne(Long id) {
        String statement = "FROM Constancia WHERE id=" + id;
        return (Constancia) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Constancia constancia) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(constancia);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(Constancia entity) {

    }
}
