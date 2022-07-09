package com.supercharger.app.services;

import com.supercharger.app.dao.VehiculosDao;
import com.supercharger.app.models.Vehiculo;

import java.util.List;

public class VehiculosService implements IGenericService{

    VehiculosDao vehiculosDao = new VehiculosDao();

    @Override
    public List<Vehiculo> findAll() {
        return this.vehiculosDao.findAll();
    }

    @Override
    public Object findOne(Long id) {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}
