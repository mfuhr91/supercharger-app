package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Cliente;

import java.util.List;

public class ClientesDao implements IGenericDao<Cliente> {

    DBConnection db;

    public ClientesDao() {
        this.db = DBConnection.getInstance();
    }

    @Override
    public List<Cliente> findAll() {
        String statement = "FROM Cliente";
        return (List<Cliente>) db.getManager().createQuery(statement).getResultList();
    }

    @Override
    public Cliente findOne(Long id) {
        String statement = "FROM Cliente WHERE id=" + id;
        return (Cliente) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Cliente cliente) {
        db.getManager().getTransaction().begin();
        db.getManager().persist(cliente);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void update(Cliente cliente) {
        db.getManager().getTransaction().begin();
        db.getManager().merge(cliente);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(Cliente cliente) {

    }

}
