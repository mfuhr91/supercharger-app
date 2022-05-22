package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Especialidad;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicosDao implements IGenericDao<Mecanico> {

    DBConnection db;

    public MecanicosDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Mecanico> findAll() {
        List<Mecanico> list = new ArrayList<>();

        try {
            String prepSt = "SELECT * FROM mecanicos;";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Mecanico mecanico = setMecanico(res);

                list.add(mecanico);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Mecanico findOne(Long id){

        Mecanico mecanico = null;
        try {
            String prepSt = "SELECT * FROM mecanicos WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();

            mecanico = setMecanico(res);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mecanico;
    }

    @Override
    public Mecanico save(Mecanico mecanico) {
        return null;
    }

    @Override
    public Mecanico update(Mecanico mecanico) {
        return null;
    }

    @Override
    public void delete(Mecanico mecanico) {

    }

    private Mecanico setMecanico(ResultSet res) {
        Mecanico mecanico = new Mecanico();
        try {
            Especialidad esp = Especialidad.getByNombre(res.getString(Constantes.ESPECIALIDAD));

            mecanico.setId(res.getLong(Constantes.ID));
            mecanico.setNombre(res.getString(Constantes.NOMBRE));
            mecanico.setEspecialidad(esp);
            mecanico.setHoraEntrada(res.getTime(Constantes.HORA_ENTRADA).toLocalTime() );
            mecanico.setHoraSalida(res.getTime(Constantes.HORA_SALIDA).toLocalTime());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mecanico;
    }
}
