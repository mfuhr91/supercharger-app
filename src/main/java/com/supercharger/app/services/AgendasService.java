package com.supercharger.app.services;

import com.supercharger.app.dao.AgendasDao;
import com.supercharger.app.dao.FichasDao;
import com.supercharger.app.models.Agenda;

import java.util.List;

public class AgendasService implements IGenericService<Agenda>{

    private AgendasDao agendasDao;

    public AgendasService() {
        this.agendasDao = new AgendasDao();
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
        return this.agendasDao.save(agenda);
    }

    @Override
    public Agenda update(Agenda entity) {
        return null;
    }

    @Override
    public void delete(Agenda entity) {

    }
}
