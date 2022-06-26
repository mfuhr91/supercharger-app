package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Agenda;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AgendasDao implements IGenericDao<Agenda> {

    DBConnection db;

    public AgendasDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Agenda> findAll() {
        return null;
    }

    @Override
    public Agenda findOne(Long id) {
        return null;
    }

    @Override
    public Agenda save(Agenda agenda) {
        String ps = "INSERT INTO agendas (mecanico_id, turno_id) VALUES (?, ?);";
        try {
            PreparedStatement statement = this.db.getConnection().prepareStatement(ps);
            statement.setLong(1, agenda.getMecanico().getId());
            statement.setLong(2, agenda.getTurno().getId());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agenda;
    }

    @Override
    public Agenda update(Agenda entity) {
        return null;
    }

    @Override
    public void delete(Agenda entity) {

    }
}
