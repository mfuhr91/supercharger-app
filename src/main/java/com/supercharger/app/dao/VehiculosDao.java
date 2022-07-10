package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Vehiculo;

import java.util.List;

public class VehiculosDao implements IGenericDao<Vehiculo> {

    DBConnection db;

    public VehiculosDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Vehiculo> findAll() {
        String statement = "FROM Vehiculo";
        return (List<Vehiculo>) db.getManager().createQuery(statement).getResultList();
    }

    @Override
    public Vehiculo findOne(Long id) {

        String statement = "FROM Vehiculo WHERE id=" + id;
        return (Vehiculo) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Vehiculo veh) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(veh);
        db.getManager().getTransaction().commit();

    }

    @Override
    public void delete(Vehiculo entity) {

    }
}
