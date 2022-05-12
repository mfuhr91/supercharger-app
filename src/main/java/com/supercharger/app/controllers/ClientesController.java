package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientesController implements Initializable{

    private static final Logger LOG = LoggerFactory.getLogger(ClientesController.class);

    @FXML
    private Button volverButton;

    @FXML
    private Button nuevoButton;

    @FXML
    private Label title;

    public static Label publicTitle;
    public static Button publicNuevoButton;

    public ClientesController() {

    }

    @FXML
    private void onNuevoClienteClick() {
        Utils.newWindow("Nuevo Cliente", "clientes", "clientesForm");
    }

    @FXML
    private void onGuardarClick() {
    }

    @FXML
    private void onVolverClick() {
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publicTitle = title;
        publicNuevoButton = nuevoButton;
    }

   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InstanceHolder holder = InstanceHolder.getInstance();
        Boolean isFromTurnos = holder.getIsfromTurnos();
        this.title = new Label();
        if (isFromTurnos) {
            this.nuevoButton = new Button();
            this.title.setText("Seleccionar Cliente");
            nuevoButton.setVisible(false);
            holder.setIsFromTurnos(false);
        }
    }*/
}
