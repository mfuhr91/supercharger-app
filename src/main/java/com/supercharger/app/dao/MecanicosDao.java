package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Mecanico;

import java.util.List;

public class MecanicosDao implements IGenericDao<Mecanico> {

    DBConnection db;

    public MecanicosDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Mecanico> findAll() {
        String statement = "FROM Mecanico";

        return (List<Mecanico>) db.getManager().createQuery(statement).getResultList();

    }

    @Override
    public Mecanico findOne(Long id){

        String statement = "FROM Mecanico WHERE id=" + id;

        return (Mecanico) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Mecanico mecanico) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(mecanico);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(Mecanico mecanico) {

    }
}
