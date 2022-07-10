package com.supercharger.app.controllers;

import com.supercharger.app.dao.TrabajosDao;
import com.supercharger.app.dataholders.TurnoHolder;
import com.supercharger.app.models.FichaMecanica;
import com.supercharger.app.models.Trabajo;
import com.supercharger.app.services.FichasService;
import com.supercharger.app.services.TrabajosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FichasController implements Initializable {

    FichasService fichasService = new FichasService();
    TrabajosService trabajosService = new TrabajosService();

    @FXML
    private TextField id;

    @FXML
    private TextField fechaTurno;

    @FXML
    private TextField cliente;

    @FXML
    private TextField mecanico;

    @FXML
    private TextArea actividad;

    @FXML
    private TextArea insumos;

    @FXML
    private Button volverButton;

    @FXML
    private void onGuardarFichaClick(){
        FichaMecanica ficha = this.fichasService.findOne(Long.parseLong(this.id.getText()));
        ficha.setActividad(this.actividad.getText());
        ficha.setInsumos(this.insumos.getText());

        this.fichasService.save(ficha);

        int secs = ficha.getTurno().getAsistencia().toLocalTime().toSecondOfDay();
        LocalTime tiempo = LocalTime.now().minus(Duration.ofSeconds(secs));

        Trabajo trabajo = this.trabajosService.findOneByFichaMecanica(ficha);
        if ( trabajo == null ){
            trabajo = new Trabajo();
            trabajo.setTiempo(tiempo);
            trabajo.setEspecialidad(ficha.getMecanico().getEspecialidad());
            trabajo.setFichaMecanica(ficha);

            this.trabajosService.save(trabajo);
        }

        Utils.newWindow("Turnos", "turnos", "turnos");
        volverButton.getScene().getWindow().hide();
    }

    @FXML
    private void onVolverClick(){
        Utils.newWindow("Turnos", "turnos", "turnos");
        volverButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TurnoHolder turnoHolder = TurnoHolder.getInstance();

        FichasService fichasService = new FichasService();

        FichaMecanica ficha = fichasService.findOneByTurnoId(turnoHolder.getTurno().getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.id.setText(ficha.getId().toString());
        this.fechaTurno.setText(ficha.getTurno().getFecha().format(formatter));
        this.cliente.setText(ficha.getCliente().getNombre() + " " + ficha.getCliente().getApellido());
        this.mecanico.setText(ficha.getMecanico().getNombre());
        this.actividad.setText(ficha.getActividad());
        this.insumos.setText(ficha.getInsumos());

        System.out.println(ficha);
    }
}
