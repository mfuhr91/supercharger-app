package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.models.Trabajo;

import javax.persistence.NoResultException;
import java.util.List;

public class TrabajosDao implements IGenericDao<Trabajo>{

    DBConnection db;

    public TrabajosDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Trabajo> findAll() {
        String statement = "FROM Trabajo";
        return (List<Trabajo>) db.getManager().createQuery(statement).getResultList();
    }

    @Override
    public Trabajo findOne(Long id) {
        String statement = "FROM Trabajo WHERE id=" + id;

        return (Trabajo) db.getManager().createQuery(statement).getSingleResult();
    }

    public Trabajo findOneByFichaMecanica(FichaMecanica fichaMecanica){
        String statement = "FROM Trabajo WHERE fichaMecanica_id=" + fichaMecanica.getId();

        try {
            return (Trabajo) db.getManager().createQuery(statement).getSingleResult();
        } catch (NoResultException ex) {
           return null;
        }

    }

    @Override
    public void save(Trabajo trabajo) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(trabajo);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(Trabajo trabajo) {

    }
}
