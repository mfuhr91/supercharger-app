package com.supercharger.app.controllers;

import com.supercharger.app.models.TurnosTableModel;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class TurnosController implements Initializable {

    @FXML
    private TableColumn<TurnosTableModel,String> nombreCliente;
    @FXML
    private TableColumn<TurnosTableModel,String> nombreMecanico;
    @FXML
    private TableColumn<TurnosTableModel,String> ingresado;
    @FXML
    private TableColumn<TurnosTableModel,String> vehiculo;
    @FXML
    private TableColumn<TurnosTableModel,String> opciones;

    @FXML
    private Button volverButton;

    @FXML
    private TableView<TurnosTableModel> table;

    public static String rowId;

    @FXML
    private void onNuevoTurnoClick(){
        Utils.newWindow("Nuevo Turno","turnos","turnosForm");
    }

    @FXML
    private void onVolverClick(){
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Button btnIngresar = new Button();
        btnIngresar.setText("Ingresar");
        btnIngresar.setId("row2");

        btnIngresar.setOnAction(e -> {
            String id =  ((Button)e.getSource()).getId();
            System.out.println(id);
        });

        Button btnFicha = new Button();
        btnFicha.setText("Ver Ficha");
        btnFicha.setId("Row1");

        btnFicha.setOnAction(e -> {
            String id =  ((Button)e.getSource()).getId();
            System.out.println(id);
            rowId = id;
            Utils.newWindow("Ficha Mecánica","fichamecanica","fichamecanica");
        });

        TurnosTableModel ttm = new TurnosTableModel("Mariano Fuhr", "Pablo Perez","SI",
                "Corsa clasic",btnFicha);

        TurnosTableModel ttm2 = new TurnosTableModel("Juan Gutierrez", "Pablo Perez","NO",
                "Corsa clasic",btnIngresar);

        nombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        nombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombreMecanico"));
        ingresado.setCellValueFactory(new PropertyValueFactory<>("ingresado"));
        vehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
        opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));

        table.getItems().add(ttm);
        table.getItems().add(ttm2);

        // get row value example onMouseClicked
        table.setRowFactory( tr -> {
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
