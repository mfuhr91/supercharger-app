package com.supercharger.app.services;

import com.supercharger.app.dao.SegurosDao;
import com.supercharger.app.dao.VehiculosDao;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.models.Vehiculo;

import java.util.List;

public class SegurosService implements IGenericService<Seguro> {

    SegurosDao segurosDao = new SegurosDao();

    @Override
    public List<Seguro> findAll() {
        return this.segurosDao.findAll();
    }

    public Seguro findByNombre(String nombre){
        return this.segurosDao.findByNombre(nombre);
    }

    @Override
    public Seguro findOne(Long id) {
        return null;
    }

    @Override
    public void save(Seguro entity) {

    }

    @Override
    public void delete(Seguro entity) {

    }
}
