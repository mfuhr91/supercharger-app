package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.tablas.AgendasTableModel;
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


public class AgendasController implements Initializable {

    @FXML
    private TableColumn<AgendasTableModel, String> nombreMecanico;
    @FXML
    private TableColumn<AgendasTableModel, String> horario;

    @FXML
    private Button volverButton;

    @FXML
    private DatePicker fechaAgendas;

    @FXML
    private TableView<AgendasTableModel> table;

    @FXML
    private void onNuevaAgendaClick() {
        Utils.newWindow("Nuevo Agenda", "agendas", "agendasForm");
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

        this.fechaAgendas.setValue(LocalDate.now());

        this.nombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombreMecanico"));
        this.horario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        List<AgendasTableModel> atmList = turnosService.getAllAgendasByFecha(LocalDate.now().toString());
        this.table.getItems().setAll(atmList);

        this.fechaAgendas.setOnAction(e -> {
            List<AgendasTableModel> atmNewList = turnosService.getAllAgendasByFecha(this.fechaAgendas.getValue().toString());
            this.table.getItems().setAll(atmNewList);
        });

        table.setRowFactory(tr -> {
            TableRow<AgendasTableModel> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    AgendasTableModel clickedRow = row.getItem();
                    System.out.println(clickedRow.getNombreMecanico());
                }
            });
            return row;
        });
    }
}
