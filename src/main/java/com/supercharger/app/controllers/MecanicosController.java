package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MecanicosController implements Initializable {

    @FXML
    private Button volverButton;

    @FXML
    private Button nuevoButton;

    @FXML
    private Label title;

    public static Label publicTitle;
    public static Button publicNuevoButton;

    @FXML
    private void onNuevoMecanicoClick(){
        Utils.newWindow("Nuevo Mecánico","mecanicos","mecanicosForm");
    }

    @FXML
    private void onGuardarClick(){
    }

    @FXML
    private void onVolverClick(){
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publicTitle = title;
        publicNuevoButton = nuevoButton;
    }
}
