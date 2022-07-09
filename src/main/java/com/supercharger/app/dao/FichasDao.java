package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.FichaMecanica;

import java.util.List;

public class FichasDao implements IGenericDao<FichaMecanica> {

    DBConnection db;

    public FichasDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List findAll() {

        String statement = "FROM FichaMecanica";
        return (List<FichaMecanica>) db.getManager().createQuery(statement).getResultList();

    }

    @Override
    public FichaMecanica findOne(Long id) {
        return findByParam(id, "id");
    }

    public FichaMecanica findOneByTurnoId(Long id) {
        return findByParam(id, "turno_id");
    }

    private FichaMecanica findByParam(Long id, String param) {

        String statement = "FROM FichaMecanica WHERE " + param + "=" + id;

        return (FichaMecanica) db.getManager().createQuery(statement).getSingleResult();

    }

    @Override
    public void save(FichaMecanica ficha) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(ficha);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void update(FichaMecanica ficha) {
        db.getManager().getTransaction().begin();
        db.getManager().merge(ficha);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(FichaMecanica ficha) {

    }
}
