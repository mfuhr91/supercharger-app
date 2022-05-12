package com.supercharger.app.controllers;

import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TurnosFormController {

    @FXML
    private Button volverButton;

    @FXML
    private void onGuardarClick(){
    }

    @FXML
    private void onGuardarFichaClick(){
    }

    @FXML
    private void onSeleccionarClienteClick(){

        Utils.newWindow("Seleccionar Cliente","clientes","clientes");

        ClientesController.publicTitle.setText("Seleccionar Cliente");
        ClientesController.publicNuevoButton.setVisible(false);

    }

    @FXML
    private void onSeleccionarMecanicoClick(){
        Utils.newWindow("Seleccionar Mecánico","mecanicos","mecanicos");

        MecanicosController.publicTitle.setText("Seleccionar Mecánico");
        MecanicosController.publicNuevoButton.setVisible(false);
    }

    @FXML
    private void onVolverClick(){
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }
}
