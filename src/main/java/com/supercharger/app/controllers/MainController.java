package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @FXML
    private Button exitButton;

    @FXML
    protected void onAgendasClick() {
        Utils.newWindow("Agendas", "agendas", "agendas");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onTurnosClick() {
        Utils.newWindow("Turnos", "turnos", "turnos");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onClientesClick() {
        Utils.newWindow("Clientes", "clientes", "clientes");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onMecanicosClick() {
        Utils.newWindow("Mecánicos", "mecanicos", "mecanicos");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onTrabajosClick() {
        Utils.newWindow("Trabajos", "trabajos", "trabajos");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onInformeClick() {
        Utils.newWindow("Informe", "turnos", "informes");
        exitButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onSalirClick() {
        System.exit(0);
    }
}
