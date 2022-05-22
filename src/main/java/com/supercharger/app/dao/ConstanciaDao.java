package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Constancia;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConstanciaDao implements IGenericDao<Constancia> {

    DBConnection db;

    public ConstanciaDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Constancia> findAll() {
        return null;
    }

    @Override
    public Constancia findOne(Long id) {
        Constancia constancia = new Constancia();
        try {
            String prepSt = "SELECT * FROM constancias WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();
            constancia.setId(res.getLong(Constantes.ID));
            constancia.setConformidad(res.getBoolean(Constantes.CONFORMIDAD));
            constancia.setMotivo(res.getString(Constantes.MOTIVO));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return constancia;
    }

    @Override
    public Constancia save(Constancia entity) {
        return null;
    }

    @Override
    public Constancia update(Constancia entity) {
        return null;
    }

    @Override
    public void delete(Constancia entity) {

    }
}
