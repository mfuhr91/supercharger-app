package com.supercharger.app.controllers;

import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.Especialidad;
import com.supercharger.app.models.Mecanico;
import com.supercharger.app.models.tablas.MecanicosTableModel;
import com.supercharger.app.services.MecanicosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MecanicosController implements Initializable {

    MecanicosService mecanicosServices = new MecanicosService();

    @FXML
    private TableColumn<MecanicosTableModel, Long> id;
    @FXML
    private TableColumn<MecanicosTableModel, String> nombre;
    @FXML
    private TableColumn<MecanicosTableModel, String> especialidad;
    @FXML
    private TableColumn<MecanicosTableModel, String> horarios;
    @FXML
    private TableView<MecanicosTableModel> table;

    @FXML
    private ComboBox espComboBox = new ComboBox();

    @FXML
    private TextField nombreForm;

    @FXML
    private TextField horarioEntrada;

    @FXML
    private TextField horarioSalida;

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
    public static boolean fromTurnos;

    @FXML
    private void onNuevoMecanicoClick() {
        Utils.newWindow("Nuevo Mecánico", "mecanicos", "mecanicosForm");
        volverButton.getScene().getWindow().hide();
    }

    @FXML
    private void onGuardarClick() {

        if( Objects.equals(this.espComboBox.getValue(), null) ||
            Objects.equals(this.nombreForm.getText(), "") ||
            Objects.equals(this.horarioEntrada.getText(), "") ||
            Objects.equals(this.horarioSalida.getText(), "") ){
            this.formOK.setVisible(true);
            return;
        }

        Mecanico mec = new Mecanico();
        mec.setNombre(this.nombreForm.getText());


        Especialidad esp = Especialidad.getByNombre((String) this.espComboBox.getValue());
        mec.setEspecialidad(esp);
        mec.setHoraEntrada(LocalTime.parse(this.horarioEntrada.getText()));
        mec.setHoraSalida(LocalTime.parse(this.horarioSalida.getText()));

        this.mecanicosServices.save(mec);
        volverButton.getScene().getWindow().hide();
        Utils.newWindow("Mecánicos", "mecanicos", "mecanicos");
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publicTitle = title;
        publicNuevoButton = nuevoButton;

        espComboBox.getItems().addAll(Especialidad.getNombres());

        if (this.table != null) {
            this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            this.especialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            this.horarios.setCellValueFactory(new PropertyValueFactory<>("horarios"));

            List<MecanicosTableModel> mtmList = mecanicosServices.findAllMecanicos();
            this.table.getItems().setAll(mtmList);

            table.setRowFactory(tr -> {
                TableRow<MecanicosTableModel> row = new TableRow<>();
                row.setOnMouseClicked(e -> {
                    if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                            && e.getClickCount() == 2) {

                        MecanicosTableModel clickedRow = row.getItem();
                        System.out.println(clickedRow.toString());

                        String[] horarios = clickedRow.getHorarios().split(" - ");

                        TurnoHolder turnoHolder = TurnoHolder.getInstance();
                        Mecanico mecanico = new Mecanico();
                        mecanico.setId(clickedRow.getId());
                        mecanico.setNombre(clickedRow.getNombre());
                        mecanico.setHoraEntrada(LocalTime.parse(horarios[0]));
                        mecanico.setHoraSalida(LocalTime.parse(horarios[1]));
                        turnoHolder.getTurno().setMecanico(mecanico);
                        ((Node) (e.getSource())).getScene().getWindow().hide();
                        if (fromTurnos) {
                            Utils.newWindow("Nuevo Turno", "turnos", "turnosForm");
                        } else {
                            Utils.newWindow("Nueva Agenda", "agendas", "agendasForm");
                        }
                        fromTurnos = false;
                    }
                });
                return row;
            });
        }
    }
}
