package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SegurosDao implements IGenericDao<Seguro> {

    DBConnection db;

    public SegurosDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Seguro findOne(Long id) {

        Seguro seguro = new Seguro();
        try {
            String prepSt = "SELECT * FROM seguros WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();
            seguro.setId(res.getLong(Constantes.ID));
            seguro.setNombre(res.getString(Constantes.NOMBRE));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seguro;
    }

    @Override
    public Seguro save(Seguro entity) {
        return null;
    }

    @Override
    public Seguro update(Seguro entity) {
        return null;
    }

    @Override
    public void delete(Seguro entity) {
    }
}
