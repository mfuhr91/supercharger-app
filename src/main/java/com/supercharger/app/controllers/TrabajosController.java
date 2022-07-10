package com.supercharger.app.controllers;

import com.supercharger.app.models.Especialidad;
import com.supercharger.app.models.Trabajo;
import com.supercharger.app.models.tablas.MecanicosTableModel;
import com.supercharger.app.models.tablas.TrabajosTableModel;
import com.supercharger.app.services.TrabajosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TrabajosController implements Initializable {

    TrabajosService trabajosService = new TrabajosService();
    @FXML
    private TableColumn<TrabajosTableModel, String> mecanico;
    @FXML
    private TableColumn<TrabajosTableModel, String> cliente;
    @FXML
    private TableColumn<TrabajosTableModel, String> especialidad;
    @FXML
    private TableColumn<TrabajosTableModel, String> tiempo;
    @FXML
    private TableView<TrabajosTableModel> table;

    @FXML
    private ComboBox espComboBox = new ComboBox();

    @FXML
    private DatePicker fecha = new DatePicker();

    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick() {
    }

    @FXML
    private void onEspChange(){
        Trabajo trabajo = null;

        getTrabajos();
    }

    @FXML
    private void onFechaChange() {
        if ( this.espComboBox.getValue() != null){
            getTrabajos();
        }
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.fecha.setValue(LocalDate.now());
        this.espComboBox.getItems().addAll(Especialidad.getNombres());

    }

    public void getTrabajos(){
        if (this.table != null) {
            this.mecanico.setCellValueFactory(new PropertyValueFactory<>("mecanico"));
            this.cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            this.especialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            this.tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));

            LocalDate fecha = this.fecha.getValue();
            Especialidad especialidad = Especialidad.getByNombre(this.espComboBox.getValue().toString());

            List<TrabajosTableModel> trabajosTableModels = this.trabajosService.findAllTrabajosByFechaByEsp(fecha, especialidad);
            this.table.getItems().setAll(trabajosTableModels);

            table.setRowFactory(tr -> {
                TableRow<TrabajosTableModel> row = new TableRow<>();
                row.setOnMouseClicked(e -> {

                    TrabajosTableModel clickedRow = row.getItem();
                    System.out.println(clickedRow.toString());
                    System.out.println(clickedRow.getCliente());
                });
                return row;
            });
        }
    }
}
