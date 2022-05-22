package com.supercharger.app.dao;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.models.Constancia;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.models.Turno;
import com.supercharger.app.utils.Constantes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FichasDao implements IGenericDao<FichaMecanica> {

    private TurnosDao turnosDao;
    private ConstanciaDao constanciaDao;

    DBConnection db;

    public FichasDao() {
        this.turnosDao = new TurnosDao();
        this.constanciaDao = new ConstanciaDao();
        try {
            this.db = DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public FichaMecanica findOne(Long id) {
        return findByParam(id, "id");
    }

    public FichaMecanica findOneByTurnoId(Long id) {
        return findByParam(id, "turno_id");
    }

    private FichaMecanica findByParam(Long id, String param) {
        FichaMecanica ficha = new FichaMecanica();
        try {
            String ps = "SELECT * FROM fichas_mecanicas WHERE " +
                    param +
                    "=" +
                    id +
                    ";";

            PreparedStatement statement = this.db.getConnection().prepareStatement(ps);
            statement.setMaxRows(1);

            ResultSet res = statement.executeQuery();
            res.next();

            Turno turno = this.turnosDao.findOne(res.getLong(Constantes.TURNO_ID));

            if (res.getLong(Constantes.CONSTANCIA_ID) != 0) {
                Constancia constancia = this.constanciaDao.findOne(res.getLong(Constantes.CONSTANCIA_ID));
                ficha.setConstancia(constancia);
            }

            ficha.setId(res.getLong(Constantes.ID));
            ficha.setActividad(res.getString(Constantes.ACTIVIDAD));
            ficha.setInsumos(res.getString(Constantes.INSUMOS));
            ficha.setTurno(turno);
            ficha.setMecanico(turno.getMecanico());
            ficha.setCliente(turno.getCliente());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ficha;
    }

    @Override
    public FichaMecanica save(FichaMecanica ficha) {
        String ps = "INSERT INTO fichas_mecanicas (actividad, insumos,constancia_id, mecanico_id, cliente_id, turno_id) VALUES ('','',0,?, ?, ?);";
        try {
            PreparedStatement statement = this.db.getConnection().prepareStatement(ps);
            statement.setLong(1, ficha.getMecanico().getId());
            statement.setLong(2, ficha.getCliente().getId());
            statement.setLong(3, ficha.getTurno().getId());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ficha;
    }

    @Override
    public FichaMecanica update(FichaMecanica ficha) {
        return null;
    }

    @Override
    public void delete(FichaMecanica ficha) {

    }
}
