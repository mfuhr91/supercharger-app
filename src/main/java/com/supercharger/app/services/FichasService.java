package com.supercharger.app.services;

import com.supercharger.app.dao.FichasDao;
import com.supercharger.app.models.FichaMecanica;

import java.util.List;

public class FichasService implements IGenericService<FichaMecanica> {

    private FichasDao fichasDao;

    public FichasService() {
        this.fichasDao = new FichasDao();
    }

    @Override
    public List<FichaMecanica> findAll() {
        return null;
    }

    @Override
    public FichaMecanica findOne(Long id) {
        return null;
    }

    public FichaMecanica findOneByTurnoId(Long id) {
        return this.fichasDao.findOneByTurnoId(id);
    }

    @Override
    public FichaMecanica save(FichaMecanica ficha) {
        return this.fichasDao.save(ficha);
    }

    @Override
    public FichaMecanica update(FichaMecanica ficha) {
        return null;
    }

    @Override
    public void delete(FichaMecanica ficha) {

    }
}
