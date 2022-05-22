package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.models.Vehiculo;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehiculosDao implements IGenericDao<Vehiculo> {

    DBConnection db;
    SegurosDao segurosDao;

    public VehiculosDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.segurosDao = new SegurosDao();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Vehiculo findOne(Long id) {

        Vehiculo veh = new Vehiculo();
        try {
            String prepSt = "SELECT * FROM vehiculos WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();
            Seguro seguro = this.segurosDao.findOne(res.getLong(Constantes.SEGURO_ID));

            veh.setId(res.getLong(Constantes.ID));
            veh.setMarca(res.getString(Constantes.MARCA));
            veh.setModelo(res.getString(Constantes.MODELO));
            veh.setNroPoliza(res.getInt(Constantes.NRO_POLIZA));
            veh.setSeguro(seguro);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veh;
    }

    @Override
    public Vehiculo save(Vehiculo entity) {
        return null;
    }

    @Override
    public Vehiculo update(Vehiculo entity) {
        return null;
    }

    @Override
    public void delete(Vehiculo entity) {

    }
}
