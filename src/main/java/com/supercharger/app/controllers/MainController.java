package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @FXML
    protected void onTurnosClick() {
        Utils.newWindow("Turnos", "turnos", "turnos");
    }

    @FXML
    protected void onClientesClick() {
        Utils.newWindow("Clientes", "clientes", "clientes");
    }

    @FXML
    protected void onMecanicosClick() {
        Utils.newWindow("Mecánicos", "mecanicos", "mecanicos");
    }

    @FXML
    protected void onTrabajosClick() {
        Utils.newWindow("Trabajos", "trabajos", "trabajos");
    }

    @FXML
    protected void onInformeClick() {
        Utils.newWindow("Informe", "turnos", "informes");
    }

    @FXML
    protected void onSalirClick() {
        System.exit(0);
    }
}
