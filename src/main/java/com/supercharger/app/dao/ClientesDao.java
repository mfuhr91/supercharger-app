package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Vehiculo;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDao implements IGenericDao<Cliente> {

    DBConnection db;
    VehiculosDao vehiculosDao;

    public ClientesDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.vehiculosDao = new VehiculosDao();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> list = new ArrayList<>();


        try {
            String prepSt = "SELECT * FROM clientes;";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Cliente cliente = setCliente(res);

                list.add(cliente);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;


    }

    @Override
    public Cliente findOne(Long id) {

        if (id == 0) {
            return null;
        }
        Cliente cliente = null;
        try {
            String prepSt = "SELECT * FROM clientes WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();

            cliente = setCliente(res);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente update(Cliente cliente) {
        return null;
    }

    @Override
    public void delete(Cliente cliente) {

    }

    private Cliente setCliente(ResultSet res) {
        Cliente cliente = new Cliente();
        try {
            cliente.setId(res.getLong(Constantes.ID));
            cliente.setNombre(res.getString(Constantes.NOMBRE));
            cliente.setApellido(res.getString(Constantes.APELLIDO));
            cliente.setTipoDoc(res.getString(Constantes.TIPO_DOC));
            cliente.setNroDoc(res.getString(Constantes.NRO_DOC));
            cliente.setTelefono(res.getInt(Constantes.TELEFONO));

            Vehiculo veh = this.vehiculosDao.findOne(res.getLong(Constantes.VEHICULO_ID));
            cliente.setVehiculo(veh);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
