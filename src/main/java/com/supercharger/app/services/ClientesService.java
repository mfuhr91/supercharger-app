package com.supercharger.app.services;

import com.supercharger.app.dao.ClientesDao;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.tablas.ClientesTableModel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ClientesService implements IGenericService<Cliente> {

    ClientesDao clientesDao = new ClientesDao();

    public List<ClientesTableModel> findAllClientes() {
        List<Cliente> clientes = this.clientesDao.findAll();

        List<ClientesTableModel> list = new ArrayList<>();

        for (Cliente cliente : clientes) {

            ClientesTableModel clientesTableModel = new ClientesTableModel();

            clientesTableModel.setId(cliente.getId());
            clientesTableModel.setNombre(cliente.getNombre());
            clientesTableModel.setApellido(cliente.getApellido());
            clientesTableModel.setNroDoc(cliente.getNroDoc());
            clientesTableModel.setTelefono(cliente.getTelefono());
            clientesTableModel.setVehiculo(cliente.getVehiculo().getMarca() + " - " + cliente.getVehiculo().getModelo());

            list.add(clientesTableModel);
        }
        return list;
    }


    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente findOne(Long id) {
        return null;
    }

    @Override
    public Cliente save(Cliente entity) {
        return null;
    }

    @Override
    public Cliente update(Cliente entity) {
        return null;
    }

    @Override
    public void delete(Cliente entity) {

    }
}
