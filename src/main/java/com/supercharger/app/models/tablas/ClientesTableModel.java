package com.supercharger.app.models.tablas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientesTableModel {
    private Long id;
    private String nombre;
    private String apellido;
    private String nroDoc;
    private int telefono;
    private String vehiculo;
}
