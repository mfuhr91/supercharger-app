package com.supercharger.app.models.tablas;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TurnosTableModel {

    private Long id;
    private String nombreCliente;
    private String nombreMecanico;
    private String ingresado;
    private String vehiculo;
    private Button opciones;
    private String disponible;
    private String horario;
}
