package com.supercharger.app.services;

import com.supercharger.app.dao.VehiculosDao;
import com.supercharger.app.models.Vehiculo;

import java.util.List;

public class VehiculosService implements IGenericService<Vehiculo> {

    VehiculosDao vehiculosDao = new VehiculosDao();

    @Override
    public List<Vehiculo> findAll() {
        return this.vehiculosDao.findAll();
    }

    @Override
    public Vehiculo findOne(Long id) {
        return this.vehiculosDao.findOne(id);
    }

    @Override
    public void save(Vehiculo entity) {

    }

    @Override
    public void delete(Vehiculo entity) {

    }
}
