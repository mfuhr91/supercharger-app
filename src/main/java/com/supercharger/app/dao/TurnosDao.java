package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;

import java.util.List;

public class TurnosDao implements IGenericDao<Turno> {

    DBConnection db;
    ClientesDao clientesDao;
    MecanicosDao mecanicosDao;

    public TurnosDao() {
        this.db = DBConnection.getInstance();
        this.clientesDao = new ClientesDao();
        this.mecanicosDao = new MecanicosDao();
    }

    @Override
    public List<Turno> findAll() {
        String statement = "FROM Turno";
        return (List<Turno>) db.getManager().createQuery(statement).getResultList();

    }

    public List<Turno> findAllNoDisponibleByFecha(String fecha) {

        String statement = "FROM Turno WHERE disponible = false AND fecha like '" + fecha + "%'";

        return  (List<Turno>) db.getManager().createQuery(statement).getResultList();
    }

    public List<Turno> findAllByFecha(String fecha) {

        String statement = "FROM Turno WHERE fecha like '" + fecha + "%'";

        return (List<Turno>) db.getManager().createQuery(statement).getResultList();
    }

    public List<Turno> findAllByFechaByMecanico(String fecha, Mecanico mecanico) {

        String statement = "FROM Turno WHERE disponible=true AND mecanico_id=" + mecanico.getId() +" AND fecha like '" + fecha + "%'";

        return (List<Turno>) db.getManager().createQuery(statement).getResultList();
    }

    @Override
    public Turno findOne(Long id) {
        String statement = "FROM Turno WHERE id=" + id;
        return (Turno) db.getManager().createQuery(statement).getSingleResult();
    }

    @Override
    public void save(Turno turno) {
        db.getManager().getTransaction().begin();
        db.getManager().merge(turno);
        db.getManager().getTransaction().commit();
    }

    @Override
    public void delete(Turno entity) {

    }
}
