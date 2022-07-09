package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Agenda;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;
import com.supercharger.app.models.tablas.TurnosTableModel;
import com.supercharger.app.services.AgendasService;
import com.supercharger.app.services.TurnosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class AgendasFormController implements Initializable {

    TurnoHolder turnoHolder = TurnoHolder.getInstance();

    TurnosService turnosService = new TurnosService();
    AgendasService agendasService = new AgendasService();

    @FXML
    private TextField mecanico;

    @FXML
    private DatePicker fechaDesde;

    @FXML
    private DatePicker fechaHasta;

    @FXML
    private TextField tiempoTurno;
    @FXML
    private Button volverButton;

    @FXML
    private Button seleccionarMecanicoButton;

    @FXML
    private void onGuardarClick() {

        Mecanico mecanico = turnoHolder.getTurno().getMecanico();

        LocalDate desde = fechaDesde.getValue();
        LocalDate hasta = fechaHasta.getValue();
        LocalTime tiempo = LocalTime.parse(tiempoTurno.getText());

        for (LocalDate fecha = desde; fecha.isBefore(hasta.plusDays(1));) {
            for (LocalTime hora = mecanico.getHoraEntrada(); hora.isBefore(mecanico.getHoraSalida());){

                Turno turno = new Turno();

                LocalDateTime fechaTurno = LocalDateTime.of(fecha,hora);
                turno.setFecha(fechaTurno);
                turno.setMecanico(mecanico);
                turno.setDisponible(true);
                this.turnosService.save(turno);

                Agenda agenda = new Agenda();
                agenda.setTurno(turno);
                agenda.setMecanico(turno.getMecanico());
                this.agendasService.save(agenda);

                hora = hora.plusHours(tiempo.getHour());
                hora = hora.plusMinutes(tiempo.getMinute());
            }
            fecha = fecha.plusDays(1);
        }


        Utils.newWindow("Agendas", "agendas", "agendas");
        seleccionarMecanicoButton.getScene().getWindow().hide();
    }

    @FXML
    private void onSeleccionarMecanicoClick() {
        Utils.newWindow("Seleccionar Mecánico", "mecanicos", "mecanicos");

        MecanicosController.publicTitle.setText("Seleccionar Mecánico");
        MecanicosController.publicNuevoButton.setVisible(false);

        seleccionarMecanicoButton.getScene().getWindow().hide();
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("Turnos", "turnos", "turnos");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TurnosService turnosService = new TurnosService();

        if (turnoHolder.getTurno() == null) {
            Turno turno = new Turno();
            turnoHolder.setTurno(turno);
        }

        Mecanico mecanico = turnoHolder.getTurno().getMecanico();

        this.fechaDesde.setValue(LocalDate.now());
        this.fechaHasta.setValue(LocalDate.now().plusDays(1));

        if (mecanico != null) {
            this.mecanico.setText(mecanico.getNombre());
        }

    }
}
