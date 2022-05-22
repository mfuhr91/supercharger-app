package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TrabajosController {
    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick() {
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }
}
