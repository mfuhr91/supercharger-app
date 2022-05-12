package com.supercharger.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InformesController {

    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick(){
    }

    @FXML
    private void onVolverClick(){
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }
}
