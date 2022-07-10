package com.supercharger.app.services;

import com.supercharger.app.dao.TrabajosDao;
import com.supercharger.app.models.Especialidad;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.models.Trabajo;
import com.supercharger.app.models.tablas.TrabajosTableModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrabajosService implements IGenericService<Trabajo>{

    TrabajosDao trabajosDao = new TrabajosDao();

    public List<TrabajosTableModel> findAllTrabajosByFechaByEsp(LocalDate fecha, Especialidad especialidad) {

        List<Trabajo> trabajos = this.trabajosDao.findAll();

        List<TrabajosTableModel> list = new ArrayList<>();

        for (Trabajo trabajo : trabajos) {

            if (trabajo.getEspecialidad().equals(especialidad) &&
                trabajo.getFichaMecanica().getTurno().getFecha().toLocalDate().equals(fecha)) {

                TrabajosTableModel trabajosTableModel = new TrabajosTableModel();

                trabajosTableModel.setMecanico(trabajo.getFichaMecanica().getMecanico().getNombre());
                trabajosTableModel.setCliente(trabajo.getFichaMecanica().getCliente().getNombre() + " " +
                        trabajo.getFichaMecanica().getCliente().getApellido());
                trabajosTableModel.setEspecialidad(trabajo.getEspecialidad().getNombre());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String tiempo = trabajo.getTiempo().format(formatter);
                trabajosTableModel.setTiempo(tiempo);

                list.add(trabajosTableModel);
            }
        }
        return list;
    }

    public List<TrabajosTableModel> findAllTrabajosBySeguroByFecha(Seguro seguro, LocalDate fecha) {

        List<Trabajo> trabajos = this.trabajosDao.findAll();

        List<TrabajosTableModel> list = new ArrayList<>();
        for (Trabajo trabajo : trabajos) {

            LocalDate fechaTrabajo = trabajo.getFichaMecanica().getTurno().getFecha().toLocalDate();
            fechaTrabajo = fechaTrabajo.withDayOfMonth(1);

            if (trabajo.getFichaMecanica().getTurno().getCliente().getVehiculo().getSeguro().equals(seguro) &&
                fecha.equals(fechaTrabajo)){
                TrabajosTableModel trabajosTableModel = new TrabajosTableModel();

                trabajosTableModel.setMecanico(trabajo.getFichaMecanica().getMecanico().getNombre());
                trabajosTableModel.setCliente(trabajo.getFichaMecanica().getCliente().getNombre() + " " +
                        trabajo.getFichaMecanica().getCliente().getApellido());
                trabajosTableModel.setEspecialidad(trabajo.getEspecialidad().getNombre());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaStr = trabajo.getFichaMecanica().getTurno().getFecha().format(formatter);
                trabajosTableModel.setTiempo(fechaStr);

                list.add(trabajosTableModel);
            }
        }
        return list;
    }

    public Trabajo findOneByFichaMecanica(FichaMecanica fichaMecanica){
        return this.trabajosDao.findOneByFichaMecanica(fichaMecanica);
    }

    @Override
    public List<Trabajo> findAll() {
        return this.trabajosDao.findAll();
    }

    @Override
    public Trabajo findOne(Long id) {
        return this.trabajosDao.findOne(id);
    }

    @Override
    public void save(Trabajo trabajo) {
        this.trabajosDao.save(trabajo);
    }

    @Override
    public void delete(Trabajo entity) {

    }
}
