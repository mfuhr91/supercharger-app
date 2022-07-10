package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Agenda;

import java.util.List;

public class AgendasDao implements IGenericDao<Agenda> {

    DBConnection db;

    public AgendasDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Agenda> findAll() {
        String statement = "FROM Agenda";
        return (List<Agenda>) db.getManager().createQuery(statement).getResultList();
    }

    @Override
    public Agenda findOne(Long id) {
        String statement = "FROM Agenda WHERE id=" + id;
        return (Agenda) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Agenda agenda) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(agenda);
        db.getManager().getTransaction().commit();

    }

    @Override
    public void delete(Agenda agenda) {

    }
}
