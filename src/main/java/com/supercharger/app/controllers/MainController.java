package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @FXML
    private Button turnosButton;

    @FXML
    protected void onTurnosClick() {
        Utils.newWindow("Turnos", "turnos", "turnos");
        turnosButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onClientesClick() {
        Utils.newWindow("Clientes", "clientes", "clientes");
        turnosButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onMecanicosClick() {
        Utils.newWindow("Mecánicos", "mecanicos", "mecanicos");
        turnosButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onTrabajosClick() {
        Utils.newWindow("Trabajos", "trabajos", "trabajos");
        turnosButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onInformeClick() {
        Utils.newWindow("Informe", "turnos", "informes");
        turnosButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onSalirClick() {
        System.exit(0);
    }
}
