package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.Turno;
import com.supercharger.app.services.TurnosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TurnosFormController implements Initializable {

    TurnoHolder turnoHolder = TurnoHolder.getInstance();

    @FXML
    private TextField fecha;

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
        System.out.println(turnoHolder.getTurno());
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fecha.setText(LocalDate.now().format(formatter));

        if (cliente != null) {
            this.cliente.setText(cliente.getNombre() + " " + cliente.getApellido());
        }

        if (mecanico != null) {
            this.mecanico.setText(mecanico.getNombre());
        }

    }
}
