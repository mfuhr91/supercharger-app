package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.tablas.ClientesTableModel;
import com.supercharger.app.services.ClientesService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    private static final Logger LOG = LoggerFactory.getLogger(ClientesController.class);

    @FXML
    private TableColumn<ClientesTableModel, Long> id;
    @FXML
    private TableColumn<ClientesTableModel, String> nombre;
    @FXML
    private TableColumn<ClientesTableModel, String> apellido;
    @FXML
    private TableColumn<ClientesTableModel, String> documento;
    @FXML
    private TableColumn<ClientesTableModel, String> telefono;
    @FXML
    private TableColumn<ClientesTableModel, String> vehiculo;
    @FXML
    private TableView<ClientesTableModel> table;

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
        this.nuevoButton.getScene().getWindow().hide();
    }

    @FXML
    private void onGuardarClick() {
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ClientesService clientesService = new ClientesService();

        publicTitle = title;
        publicNuevoButton = nuevoButton;

        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.documento.setCellValueFactory(new PropertyValueFactory<>("nroDoc"));
        this.telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.vehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));

        List<ClientesTableModel> ctmList = clientesService.findAllClientes();
        this.table.getItems().setAll(ctmList);

        table.setRowFactory(tr -> {
            TableRow<ClientesTableModel> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    ClientesTableModel clickedRow = row.getItem();
                    System.out.println(clickedRow.toString());

                    TurnoHolder turnoHolder = TurnoHolder.getInstance();
                    Cliente cliente = new Cliente();
                    cliente.setId(clickedRow.getId());
                    cliente.setNombre(clickedRow.getNombre());
                    cliente.setApellido(clickedRow.getApellido());
                    turnoHolder.getTurno().setCliente(cliente);
                    ((Node) (e.getSource())).getScene().getWindow().hide();
                    Utils.newWindow("Nuevo Turno", "turnos", "turnosForm");
                }
            });
            return row;
        });
    }
}
