package com.supercharger.app.services;

import com.supercharger.app.dao.MecanicosDao;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.tablas.ClientesTableModel;
import com.supercharger.app.models.tablas.MecanicosTableModel;

import java.util.ArrayList;
import java.util.List;

public class MecanicosService implements IGenericService<Mecanico> {

    MecanicosDao mecanicosDao = new MecanicosDao();

    public List<MecanicosTableModel> findAllMecanicos() {
        List<Mecanico> mecanicos = this.mecanicosDao.findAll();

        List<MecanicosTableModel> list = new ArrayList<>();

        for (Mecanico mecanico : mecanicos) {

            MecanicosTableModel mecanicosTableModel = new MecanicosTableModel();

            mecanicosTableModel.setId(mecanico.getId());
            mecanicosTableModel.setNombre(mecanico.getNombre());
            mecanicosTableModel.setEspecialidad(mecanico.getEspecialidad().getNombre());

            String horarios = mecanico.getHoraEntrada() + " - " + mecanico.getHoraSalida();
            mecanicosTableModel.setHorarios(horarios);

            list.add(mecanicosTableModel);
        }
        return list;
    }
    @Override
    public List<Mecanico> findAll() {
        return null;
    }

    @Override
    public Mecanico findOne(Long id) {
        return null;
    }

    @Override
    public void save(Mecanico mecanico) {
        this.mecanicosDao.save(mecanico);
    }

    @Override
    public void delete(Mecanico entity) {

    }
}
