package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TurnosDao implements IGenericDao<Turno> {

    DBConnection db;
    ClientesDao clientesDao;
    MecanicosDao mecanicosDao;

    public TurnosDao() {
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.clientesDao = new ClientesDao();
        this.mecanicosDao = new MecanicosDao();
    }

    @Override
    public List<Turno> findAll() {

        List<Turno> list = new ArrayList<>();
        try {
            String prepSt = "SELECT * FROM turnos;";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Turno turno = new Turno();

                Cliente cliente = this.clientesDao.findOne(res.getLong(Constantes.CLIENTE_ID));
                Mecanico mecanico = this.mecanicosDao.findOne(res.getLong(Constantes.MECANICO_ID));

                turno.setId(res.getLong(Constantes.ID));
                turno.setMecanico(mecanico);
                turno.setFecha(res.getDate(Constantes.FECHA).toLocalDate().atStartOfDay());
                turno.setCliente(cliente);
                turno.setDisponible(res.getBoolean(Constantes.DISPONIBLE));
                if (res.getDate(Constantes.ASISTENCIA) != null) {
                    LocalTime localTime = res.getTime(Constantes.ASISTENCIA).toLocalTime();
                    turno.setAsistencia(res.getDate(Constantes.ASISTENCIA).toLocalDate().atTime(localTime));
                }
                list.add(turno);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Turno> findAllByFecha(String fecha) {

        List<Turno> list = new ArrayList<>();
        try {
            String prepSt = "SELECT * FROM turnos WHERE disponible = false AND fecha like '" + fecha + "%';";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Turno turno = new Turno();

                Cliente cliente = this.clientesDao.findOne(res.getLong(Constantes.CLIENTE_ID));
                Mecanico mecanico = this.mecanicosDao.findOne(res.getLong(Constantes.MECANICO_ID));

                turno.setId(res.getLong(Constantes.ID));
                turno.setMecanico(mecanico);
                turno.setFecha(res.getDate(Constantes.FECHA).toLocalDate().atStartOfDay());
                turno.setCliente(cliente);
                turno.setDisponible(res.getBoolean(Constantes.DISPONIBLE));
                if (res.getDate(Constantes.ASISTENCIA) != null) {
                    LocalTime localTime = res.getTime(Constantes.ASISTENCIA).toLocalTime();
                    turno.setAsistencia(res.getDate(Constantes.ASISTENCIA).toLocalDate().atTime(localTime));
                }
                list.add(turno);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Turno findOne(Long id) {

        Turno turno = new Turno();
        try {
            String prepSt = "SELECT * FROM turnos WHERE id=" + id + ";";
            PreparedStatement statement = this.db.getConnection().prepareStatement(prepSt);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();

            Cliente cliente = this.clientesDao.findOne(res.getLong(Constantes.CLIENTE_ID));
            Mecanico mecanico = this.mecanicosDao.findOne(res.getLong(Constantes.MECANICO_ID));

            turno.setId(res.getLong(Constantes.ID));
            turno.setMecanico(mecanico);
            turno.setFecha(res.getDate(Constantes.FECHA).toLocalDate().atStartOfDay());
            turno.setCliente(cliente);
            turno.setDisponible(res.getBoolean(Constantes.DISPONIBLE));
            turno.setAsistencia(res.getDate(Constantes.ASISTENCIA).toLocalDate().atStartOfDay());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turno;
    }

    @Override
    public Turno save(Turno entity) {
        return null;
    }

    @Override
    public Turno update(Turno turno) {
        try {
            StringBuilder ps = new StringBuilder();
            ps.append("UPDATE turnos SET");

            if (turno.getMecanico() != null) {
                ps.append(" mecanico_id=");
                ps.append(turno.getMecanico().getId());
                ps.append(",");
            }

            if (turno.getFecha() != null) {
                ps.append(" fecha='");
                ps.append(turno.getFecha());
                ps.append("',");
            }

            if (turno.getCliente() != null) {
                ps.append(" cliente_id=");
                ps.append(turno.getCliente().getId());
                ps.append(",");
            }

            if (turno.getAsistencia() != null) {
                ps.append(" asistencia='");
                ps.append(turno.getAsistencia());
                ps.append("',");
            }

            ps.append(" disponible=");
            ps.append(turno.isDisponible());

            ps.append(" WHERE id=?;");

            PreparedStatement statement = this.db.getConnection().prepareStatement(ps.toString());

            statement.setLong(1, turno.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turno;
    }

    @Override
    public void delete(Turno entity) {

    }
}
