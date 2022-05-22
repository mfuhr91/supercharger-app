package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.services.FichasService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FichasController implements Initializable {

    @FXML
    private TextField fechaTurno;

    @FXML
    private TextField cliente;

    @FXML
    private TextField mecanico;

    @FXML
    private TextArea actividad;

    @FXML
    private TextArea insumos;

    @FXML
    private Button volverButton;

    @FXML
    private void onGuardarClick(){
    }

    @FXML
    private void onGuardarFichaClick(){
    }

    @FXML
    private void onVolverClick(){
        Utils.newWindow("Turnos", "turnos", "turnos");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TurnoHolder turnoHolder = TurnoHolder.getInstance();

        FichasService fichasService = new FichasService();

        FichaMecanica ficha = fichasService.findOneByTurnoId(turnoHolder.getTurno().getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.fechaTurno.setText(ficha.getTurno().getFecha().format(formatter));
        this.cliente.setText(ficha.getCliente().getNombre() + " " + ficha.getCliente().getApellido());
        this.mecanico.setText(ficha.getMecanico().getNombre());
        this.actividad.setText(ficha.getActividad());
        this.insumos.setText(ficha.getInsumos());

        System.out.println(ficha);
    }
}
