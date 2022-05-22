package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.tablas.TurnosTableModel;
import com.supercharger.app.services.TurnosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class TurnosController implements Initializable {

    @FXML
    private TableColumn<TurnosTableModel, String> nombreCliente;
    @FXML
    private TableColumn<TurnosTableModel, String> nombreMecanico;
    @FXML
    private TableColumn<TurnosTableModel, String> ingresado;
    @FXML
    private TableColumn<TurnosTableModel, String> vehiculo;
    @FXML
    private TableColumn<TurnosTableModel, String> opciones;

    @FXML
    private Button volverButton;

    @FXML
    private DatePicker fechaTurnos;

    @FXML
    private TableView<TurnosTableModel> table;

    @FXML
    private void onNuevoTurnoClick() {
        Utils.newWindow("Nuevo Turno", "turnos", "turnosForm");
        volverButton.getScene().getWindow().hide();
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TurnosService turnosService = new TurnosService();
        TurnoHolder turnoHolder = TurnoHolder.getInstance();
        turnoHolder.setTurno(null);

        this.fechaTurnos.setValue(LocalDate.now());

        this.nombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        this.nombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombreMecanico"));
        this.ingresado.setCellValueFactory(new PropertyValueFactory<>("ingresado"));
        this.vehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
        this.opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));

        List<TurnosTableModel> ttmList = turnosService.getAllTurnosByFecha(LocalDate.now().toString());
        this.table.getItems().setAll(ttmList);

        this.fechaTurnos.setOnAction(e -> {
            List<TurnosTableModel> ttmNewList = turnosService.getAllTurnosByFecha(this.fechaTurnos.getValue().toString());
            this.table.getItems().setAll(ttmNewList);
        });

        table.setRowFactory(tr -> {
            TableRow<TurnosTableModel> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    TurnosTableModel clickedRow = row.getItem();
                    System.out.println(clickedRow.getNombreCliente());
                }
            });
            return row;
        });
    }
}
