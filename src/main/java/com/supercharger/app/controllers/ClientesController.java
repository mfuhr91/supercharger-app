package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Cliente;
import com.supercharger.app.models.Vehiculo;
import com.supercharger.app.models.tablas.ClientesTableModel;
import com.supercharger.app.services.ClientesService;
import com.supercharger.app.services.VehiculosService;
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
import java.util.*;

public class ClientesController implements Initializable {

    private static final Logger LOG = LoggerFactory.getLogger(ClientesController.class);

    VehiculosService vehiculosService = new VehiculosService();
    ClientesService clientesService = new ClientesService();
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
    private ComboBox vehCombo = new ComboBox();
    @FXML
    private TextField nomForm;
    @FXML
    private TextField apeForm;
    @FXML
    private ComboBox tDocCombo = new ComboBox();
    @FXML
    private TextField docForm;
    @FXML
    private TextField telForm;

    @FXML
    private Button volverButton;

    @FXML
    private Button nuevoButton;

    @FXML
    private Label title;

    @FXML
    private Label formOK = new Label();

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

        if( Objects.equals(this.vehCombo.getValue(), null) ||
            Objects.equals(this.tDocCombo.getValue(), null) ||
            Objects.equals(this.nomForm.getText(), "") ||
            Objects.equals(this.apeForm.getText(), "") ||
            Objects.equals(this.docForm.getText(), "") ){
            this.formOK.setVisible(true);
            return;
        }

        String comboValue =  this.vehCombo.getValue().toString();
        Cliente cliente = new Cliente();
        cliente.setNombre(this.nomForm.getText());
        cliente.setApellido(this.apeForm.getText());
        cliente.setTipoDoc(this.tDocCombo.getValue().toString());
        cliente.setNroDoc(this.docForm.getText());

        if (this.telForm.getText() != "") {
            cliente.setTelefono(Long.parseLong(this.telForm.getText()));
        }

        Vehiculo vehiculo = null;
        if ( comboValue != "") {
            vehiculo = this.vehiculosService.findOne(Long.parseLong(comboValue.split(" - ")[0]));
        }
        cliente.setVehiculo(vehiculo);

        this.clientesService.save(cliente);

        volverButton.getScene().getWindow().hide();
        Utils.newWindow("Clientes", "clientes", "clientes");
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ClientesService clientesService = new ClientesService();

        List<Vehiculo> vehs = this.vehiculosService.findAll();
        vehs.forEach(veh -> {
            this.vehCombo.getItems().add(veh.getId() + " - " + veh.getMarca() + " " + veh.getModelo());
        });
        this.tDocCombo.getItems().addAll(Arrays.asList("DNI","CEDULA","PASAPORTE"));

        publicTitle = title;
        publicNuevoButton = nuevoButton;
        if (this.table != null) {
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
                TurnoHolder turnoHolder = TurnoHolder.getInstance();
                row.setOnMouseClicked(e -> {
                    if (!row.isEmpty()
                            && e.getButton() == MouseButton.PRIMARY
                            && e.getClickCount() == 2
                            && turnoHolder.getTurno() != null) {

                        ClientesTableModel clickedRow = row.getItem();
                        System.out.println(clickedRow.toString());

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
}
