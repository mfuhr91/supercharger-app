package com.supercharger.app.models;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class TurnosTableModel {
    private String nombreCliente;
    private String nombreMecanico;
    private String ingresado;
    private String vehiculo;
    private Button opciones;
}
