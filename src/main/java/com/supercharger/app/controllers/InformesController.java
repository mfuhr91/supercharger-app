package com.supercharger.app.controllers;

import com.supercharger.app.dao.SegurosDao;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.models.tablas.TrabajosTableModel;
import com.supercharger.app.services.SegurosService;
import com.supercharger.app.services.TrabajosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class InformesController implements Initializable {

    TrabajosService trabajosService = new TrabajosService();
    SegurosService segurosService = new SegurosService();

    @FXML
    private TableColumn<TrabajosTableModel, String> mecanico;
    @FXML
    private TableColumn<TrabajosTableModel, String> cliente;
    @FXML
    private TableColumn<TrabajosTableModel, String> especialidad;
    @FXML
    private TableColumn<TrabajosTableModel, String> fecha;
    @FXML
    private TableView<TrabajosTableModel> table;

    @FXML
    private ComboBox seguros = new ComboBox();

    @FXML
    private ComboBox month = new ComboBox();

    @FXML
    private ComboBox year = new ComboBox();

    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick() {
    }

    @FXML
    private void onSeguroChange(){
        getTrabajos();
    }

    @FXML
    private void onMonthChange(){
        if(this.seguros.getValue() != null){
            getTrabajos();
        }
    }
    @FXML
    private void onYearChange(){
        if(this.seguros.getValue() != null){
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

        List<Seguro> seguroList = this.segurosService.findAll();
        for (Seguro seguro: seguroList) {
            this.seguros.getItems().add(seguro.getNombre());
        }

        for (int i = 1; i <= 12; i++) {
            this.month.getItems().add(i);
        }
        this.year.getItems().addAll(Arrays.asList(2021,2022,2023,2024));

        this.month.setValue(LocalDate.now().getMonthValue());
        this.year.setValue(LocalDate.now().getYear());


    }

    public void getTrabajos(){
        if (this.table != null) {
            this.mecanico.setCellValueFactory(new PropertyValueFactory<>("mecanico"));
            this.cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            this.especialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            this.fecha.setCellValueFactory(new PropertyValueFactory<>("tiempo"));


            LocalDate fecha = LocalDate.of((Integer)year.getValue(),(Integer)month.getValue(),1);
            Seguro seguro = this.segurosService.findByNombre(this.seguros.getValue().toString());
            List<TrabajosTableModel> trabajosTableModels = this.trabajosService.findAllTrabajosBySeguroByFecha(seguro,fecha);
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
