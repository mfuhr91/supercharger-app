package com.supercharger.app.services;

import com.supercharger.app.dao.TurnosDao;
import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;
import com.supercharger.app.models.tablas.AgendasTableModel;
import com.supercharger.app.models.tablas.TurnosTableModel;
import com.supercharger.app.utils.Utils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TurnosService implements IGenericService<Turno> {

    private TurnosDao turnosDao;
    private FichasService fichasService;

    private Long turnoId;

    private Button btnIngresar;
    private Button btnFicha;

    public TurnosService() {
        this.turnosDao = new TurnosDao();
        this.fichasService = new FichasService();
    }

    public List<TurnosTableModel> getAllTurnosByFecha(String fecha) {
        List<Turno> list = turnosDao.findAllNoDisponibleByFecha(fecha);
        List<TurnosTableModel> ttmList = new ArrayList<>();

        for (Turno turno : list) {

            this.btnIngresar = new Button();
            btnIngresar.setText("Ingresar");
            btnIngresar.setId("btnIngresar-" + turno.getId());


            this.btnFicha = new Button();
            btnFicha.setText("Ver Ficha");
            btnFicha.setId("btnFicha-" + turno.getId());

            btnFicha.setOnAction(e -> {
                String btnId = ((Button) e.getSource()).getId();
                String[] arrBtnId = btnId.split("-");

                System.out.println(btnId);


                Turno turnoSelect = new Turno();
                turnoSelect.setId(Long.parseLong(arrBtnId[1]));

                TurnoHolder turnoHolder = TurnoHolder.getInstance();
                turnoHolder.setTurno(turnoSelect);

                ((Node) (e.getSource())).getScene().getWindow().hide();
                Utils.newWindow("Ficha Mecánica", "fichamecanica", "fichamecanica");
            });

            btnIngresar.setOnAction(e -> {
                String btnId = ((Button) e.getSource()).getId();

                String[] arrBtnId = btnId.split("-");

                Turno turnoUpdate = this.turnosDao.findOne(Long.parseLong(arrBtnId[1]));
                System.out.println(turnoUpdate);
                System.out.println(arrBtnId[1]);
                turnoUpdate.setAsistencia(LocalDateTime.now());

                this.turnosDao.save(turnoUpdate);

                FichaMecanica ficha = new FichaMecanica();
                ficha.setTurno(turnoUpdate);
                ficha.setCliente(turnoUpdate.getCliente());
                ficha.setMecanico(turnoUpdate.getMecanico());

                this.fichasService.save(ficha);

                ((Node) (e.getSource())).getScene().getWindow().hide();
                Utils.newWindow("Turnos", "turnos", "turnos");
            });

            Cliente cliente = turno.getCliente();
            boolean ingresado = turno.getAsistencia() != null;

            String datosCliente;
            String datosVehiculo;

            datosCliente = cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : "";
            datosVehiculo = (cliente != null && cliente.getVehiculo() != null) ? cliente.getVehiculo().getMarca() + " - " + cliente.getVehiculo().getModelo() : "";

            TurnosTableModel ttm = new TurnosTableModel();
            ttm.setNombreCliente(datosCliente);
            ttm.setNombreMecanico(turno.getMecanico().getNombre());
            ttm.setIngresado(ingresado ? "SI" : "NO");
            ttm.setVehiculo(datosVehiculo);
            ttm.setOpciones(ingresado ? btnFicha : btnIngresar);

            ttmList.add(ttm);
        }
        return ttmList;
    }

    public List<AgendasTableModel> getAllAgendasByFecha(String fecha) {
        List<Turno> list = turnosDao.findAllByFecha(fecha);
        List<AgendasTableModel> atmList = new ArrayList<>();

        for (Turno turno : list) {
            String horario = turno.getFecha().toLocalTime().toString();
            AgendasTableModel atm =
                    new AgendasTableModel(turno.getMecanico().getNombre(), horario);
            atmList.add(atm);
        }
        return atmList;
    }

    @Override
    public List<Turno> findAll() {
        return null;
    }


    @Override
    public Turno findOne(Long id) {
        return null;
    }

    @Override
    public void save(Turno turno) {
        this.turnosDao.save(turno);
    }

    @Override
    public void delete(Turno entity) {

    }

    public List<TurnosTableModel> getAllTurnosByFechaByMecanico(String fecha, Mecanico mecanico) {
        List<Turno> list = turnosDao.findAllByFechaByMecanico(fecha, mecanico);
        List<TurnosTableModel> ttmList = new ArrayList<>();

        for (Turno turno : list) {


            TurnosTableModel ttm = new TurnosTableModel();
            ttm.setId(turno.getId());
            ttm.setHorario(turno.getFecha().toLocalTime().toString());
            ttm.setDisponible(turno.isDisponible() ? "SI" : "NO");

            ttmList.add(ttm);
        }
        return ttmList;
    }
}
