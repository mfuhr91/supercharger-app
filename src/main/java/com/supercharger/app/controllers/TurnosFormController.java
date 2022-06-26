package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;
import com.supercharger.app.models.tablas.MecanicosTableModel;
import com.supercharger.app.models.tablas.TurnosTableModel;
import com.supercharger.app.services.TurnosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class TurnosFormController implements Initializable {

    TurnosService turnosService = new TurnosService();
    TurnoHolder turnoHolder = TurnoHolder.getInstance();

    @FXML
    private TableColumn<MecanicosTableModel, Long> id;
    @FXML
    private TableColumn<TurnosTableModel, String> horario;
    @FXML
    private TableColumn<TurnosTableModel, String> disponible;
    @FXML
    private TableView<TurnosTableModel> table;

    @FXML
    private DatePicker fechaTurnos;

    @FXML
    private TextField cliente;

    @FXML
    private TextField mecanico;

    @FXML
    private Button volverButton;

    @FXML
    private Button seleccionarClienteButton;

    @FXML
    private Button seleccionarMecanicoButton;


    @FXML
    private void onGuardarClick() {

        if ( turnoHolder.getTurno().getCliente() != null && turnoHolder.getTurno().getFecha() != null) {
            this.turnosService.update(turnoHolder.getTurno());
           onVolverClick();
        }

    }

    @FXML
    private void onSeleccionarClienteClick() {

        Utils.newWindow("Seleccionar Cliente", "clientes", "clientes");

        ClientesController.publicTitle.setText("Seleccionar Cliente");
        ClientesController.publicNuevoButton.setVisible(false);

        seleccionarClienteButton.getScene().getWindow().hide();

    }

    @FXML
    private void onSeleccionarMecanicoClick() {
        Utils.newWindow("Seleccionar Mecánico", "mecanicos", "mecanicos");

        MecanicosController.publicTitle.setText("Seleccionar Mecánico");
        MecanicosController.publicNuevoButton.setVisible(false);
        MecanicosController.fromTurnos = true;

        seleccionarMecanicoButton.getScene().getWindow().hide();
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("Turnos", "turnos", "turnos");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (turnoHolder.getTurno() == null) {
            Turno turno = new Turno();
            turnoHolder.setTurno(turno);
        }

        Cliente cliente = turnoHolder.getTurno().getCliente();
        Mecanico mecanico = turnoHolder.getTurno().getMecanico();

        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        this.disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        this.mecanico.setOnInputMethodTextChanged(e -> {
        });

        this.fechaTurnos.setValue(LocalDate.now());
        this.fechaTurnos.setOnAction(e -> {
            List<TurnosTableModel> ttmNewList = turnosService.getAllTurnosByFechaByMecanico(this.fechaTurnos.getValue().toString(), mecanico);
            this.table.getItems().setAll(ttmNewList);
        });

        if ( mecanico != null) {
            System.out.println(mecanico);
            List<TurnosTableModel> ttmList = turnosService.getAllTurnosByFechaByMecanico(this.fechaTurnos.getValue().toString(), mecanico);
            this.table.getItems().setAll(ttmList);
        }

        if (cliente != null) {
            this.cliente.setText(cliente.getNombre() + " " + cliente.getApellido());
        }

        if (mecanico != null) {
            this.mecanico.setText(mecanico.getNombre());
        }

        table.setRowFactory(tr -> {
            TableRow<TurnosTableModel> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 1) {

                    TurnosTableModel clickedRow = row.getItem();

                    LocalDateTime fecha = LocalDateTime.of(fechaTurnos.getValue(),LocalTime.parse(clickedRow.getHorario()));
                    turnoHolder.getTurno().setId(clickedRow.getId());
                    turnoHolder.getTurno().setFecha(fecha);
                    System.out.println(turnoHolder);
                }
            });
            return row;
        });
    }
}
